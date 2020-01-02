package cn.aynu.job.dao;

import cn.aynu.commons.beans.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IJobDao {
    @SelectProvider(type = JobSqlProvider.class, method = "selectJobCount")
    int selectJobCount(String name);

    @SelectProvider(type = JobSqlProvider.class, method = "selectJob")
    List<Job> selectJob(Map map);

    @Select("select * from job_inf where id=#{id}")
    Job selectJobById(Integer id);

    @Update("update job_inf set name=#{name},remark=#{remark}  where id=#{id}")
    int updateJob(Job job);

    @DeleteProvider(type = JobSqlProvider.class, method = "deleteJob")
    int deleteJob(@Param("ids") int[] ids);

    @Insert("insert into job_inf (name,remark) values(#{name},#{remark})")
    int insertJob(Job job);
}
