package cn.aynu.job.service;

import cn.aynu.commons.beans.Job;
import cn.aynu.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IJobService {
    int findJobCount(String name);

    List<Job> findJob(String name, PageModel pageModel);

    Job findJobById(Integer id);

    int updateJob(Job job);

    int removeJob(int[] id);

    int addJob(Job job);
}
