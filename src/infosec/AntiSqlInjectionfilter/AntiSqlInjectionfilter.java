package infosec.AntiSqlInjectionfilter;

import java.io.IOException;
import java.util.Enumeration;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AntiSqlInjectionfilter implements Filter {
	 
    public void destroy() {
        // TODO Auto-generated method stub
    }
     
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
     
    public void doFilter(ServletRequest args0, ServletResponse args1,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)args0;
        //HttpServletRequest res=(HttpServletRequest)args1;
         //����������������
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //�õ�������
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //�õ�������Ӧֵ
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        //System.out.println("============================SQL"+sql);
        //��sql�ؼ��֣���ת��error.html
        if (sqlValidate(sql)) {
            throw new IOException("�����������еĲ����к��зǷ��ַ�");
            //String ip = req.getRemoteAddr();
        } else {
            chain.doFilter(args0,args1);
        }
    }
     
    //Ч��
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//ͳһתΪСд
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//���˵���sql�ؼ��֣������ֶ����
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
}
