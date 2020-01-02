package cn.aynu.job.service;

import cn.aynu.commons.beans.Job;
import cn.aynu.job.dao.IJobDao;
import cn.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JobServiceImpl implements IJobService {
    @Resource
    private IJobDao jobDao;

    @Override
    public int findJobCount(String name) {
        int rows=jobDao.selectJobCount(name);
        //System.out.println(rows);
        return rows;
    }

    @Override
    public List<Job> findJob(String name, PageModel pageModel) {
        Map map=new HashMap();
        map.put("name",name);
        map.put("pageModel",pageModel);
        return jobDao.selectJob(map);
    }

    @Override
    public Job findJobById(Integer id) {
        return jobDao.selectJobById(id);
    }

    @Override
    public int updateJob(Job job) {
        return jobDao.updateJob(job);
    }

    @Override
    public int removeJob(int[] ids) {
        return jobDao.deleteJob(ids);
    }

    @Override
    public int addJob(Job job) {
        return jobDao.insertJob(job);
    }
}
