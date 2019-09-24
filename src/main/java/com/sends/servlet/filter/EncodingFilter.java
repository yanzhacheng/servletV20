package com.sends.servlet.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 说明：全站字符乱码过滤器
 * 注意：过滤器编写好后,需要去web.xml文件中配置才能使用,在默认情况下,服务器启动时创建过滤器对象
 * 特点：可以解决HTTP请求（GET & POST）提交给服务器的数据出现乱码的问题
 * 思路：
 *       ①动态代理
 *       ②使用HttpServletRequestWrapper类（本案例采用的是它）
 */
public class EncodingFilter implements Filter
{
    private String charset;

    public void destroy(){}

    public void init(FilterConfig filterConfig) throws ServletException
    {
        charset = filterConfig.getInitParameter("charset"); //从web.xml文件内的当前过滤器配置中获取设置的字符编码参数
        charset = (charset==null? "UTF-8" : charset); //若没有在web.xml文件中配置,则charset值设置为UTF-8
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding(charset); //为request对象设置码表
        PowerRequest powerRequest = new PowerRequest((HttpServletRequest)request); //创建request对象的包装对象（一个对request对象进行增强的对象）
        chain.doFilter(powerRequest, response); //对请求进行放行
    }
}

/**
 * 说明：
 *      ①在JavaEE规范中专门提供了一个采用装饰设计模式的类HttpServletRequestWrapper
 *      ②在HttpServletRequestWrapper类的内部,其实已经定义了一个类型为HttpServletRequest的成员变量
 *      ③在HttpServletRequestWrapper类中的所有方法,其实都是使用它内部的HttpServletRequest类型的成员变量去掉用的
 *      ④该HttpServletRequestWrapper类的作用就是专门提供给程序使用的,程序员用来重写其某些方法,从而达到"增强请求对象"的目的
 *
 * 思路：通过自己编写一个类来继承HttpServletRequestWrapper,然后重写该类的某些方法,从而达到解决中文乱码问题
 */
class PowerRequest extends HttpServletRequestWrapper
{
    private String charset;

    //构造函数
    public PowerRequest(HttpServletRequest request)
    {
        super(request);
        charset = super.getCharacterEncoding(); //若之前有人为的设置过request对象的编码,则获取成功,否则获取为null
        charset = (charset == null? "ISO-8859-1" : charset);
    }


    @Override
    public String getParameter(String name)
    {
        if("GET".equalsIgnoreCase(super.getMethod()))
        {
            String data = super.getParameter(name); //获取请求参数数据
            if(data != null)
            {
                try
                {
                    data = new String(data.getBytes("ISO-8859-1"), charset);
                }
                catch(UnsupportedEncodingException e)
                {
                    throw new RuntimeException(e);
                }
            }
            return data;
        }
        return super.getParameter(name);
    }


    @Override
    public String[] getParameterValues(String name)
    {
        if("GET".equalsIgnoreCase(super.getMethod()))
        {
            String[] values = super.getParameterValues(name);
            for(int i=0; values!=null && i<values.length; i++)
            {
                try
                {
                    values[i] = new String(values[i].getBytes("ISO-8859-1"), charset);
                }
                catch(UnsupportedEncodingException e)
                {
                    throw new RuntimeException(e);
                }
            }
            return values;
        }
        return super.getParameterValues(name);
    }


    @Override
    public Map getParameterMap()
    {
        if("GET".equalsIgnoreCase(super.getMethod()))
        {
            Map<String,String[]> map = super.getParameterMap();
            if(map != null)
            {
                for(Map.Entry<String,String[]> me : map.entrySet())
                {
                    String[] values = me.getValue();
                    for(int i=0; values!=null && i<values.length; i++)
                    {
                        try
                        {
                            values[i] = new String(values[i].getBytes("ISO-8859-1"), charset);
                        }
                        catch(UnsupportedEncodingException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            return map;
        }
        return super.getParameterMap();
    }

}
