package cn.aynu.dept.dao;


import cn.aynu.utils.PageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/14
 */
public class DeptSqlProvider {
    public String selectDeptCount(final String name) {
        String sql = new SQL() {
            {
                this.SELECT("count(*)");
                this.FROM("dept_inf");
                if (name != null && !name.equals("")) {
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        //System.out.println("查找数量sql"+sql);
        return sql;
    }

    public String selectDept(Map map) {
        final String name = (String) map.get("name");
        final PageModel pageModel = (PageModel) map.get("pageModel");

        String sql = new SQL() {
            {
                this.SELECT("*");
                this.FROM("dept_inf");
                if (name != null && !name.equals("")) {
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        sql = sql + " limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        //System.out.println("查找sql"+sql);
        return sql;
    }

    public String deleteDept(int[] ids) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from dept_inf where id in(");
        for (int id : ids
        ) {
            sql.append(id + ",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        //System.out.println(sql);
        return sql.toString();
    }
}
