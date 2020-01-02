package cn.aynu.job.dao;


import cn.aynu.utils.PageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/14
 */
public class JobSqlProvider {
    public String selectJobCount(final String name) {
        //返回的sql语句也可以使用字符串拼接
        //利用stringBuffer
        String sql = new SQL() {
            {
                this.SELECT("count(*)");
                this.FROM("job_inf");
                if (name != null && !name.equals("")) {
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        //System.out.println("查找数量sql"+sql);
        return sql;
    }

    public String selectJob(Map map) {
        final String name = (String) map.get("name");
        final PageModel pageModel = (PageModel) map.get("pageModel");

        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("job_inf");
                if (name != null && !name.equals("")) {
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        sql = sql + " limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        //System.out.println("查找sql"+sql);
        return sql;
    }

    public String deleteJob(int[] ids) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from job_inf where id in(");
        for (int id : ids
        ) {
            System.out.println("删除的id：" + id);
            sql.append(id + ",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        //System.out.println(sql);
        return sql.toString();
    }
}
