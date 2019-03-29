package com.qsmx.zww.servcie;

import com.qsmx.zww.mapper.ZhiLianJobMapper;
import com.qsmx.zww.uitil.HttpClient;
import com.qsmx.zww.uitil.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-28.
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private ZhiLianJobMapper zhiLianJobMapper;

    @Override
    public String job() {
        try {
            Map<String, String> headers = new HashMap();
            Map<String, String> parm = new HashMap();
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
            headers.put("Accept-Language", "zh-CN,zh;q=0.9");
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headers.put("Host","fe-api.zhaopin.com");
            headers.put("Connection","keep-alive");
            headers.put("Upgrade-Insecure-Requests", "1");
            headers.put("Cookie", "acw_tc=2760825215537526938315910e551b860f25e8dd0088156b6b22ccc70bea02;" +
                    " acw_sc__v3=5c9c627548464fc8b5c554a24cb26acc1336a44c; acw_sc__v2=5c9c62753513e0f20ce84860db56777813f9e2ee");
            a:
            for (int i = 100; i < 1100; i += 100) {
                String jsonStr = HttpClient.doGet("https://fe-api.zhaopin.com/c/i/sou?start=" + i + "&pageSize=90&cityId=489&workExperience=-1" +
                        "&education=-1&companyType=-1&employmentType=-1&jobWelfareTag=-1&kw=java&kt=3&_v=0.78934111&x-zp-page-request-id=5ad692d2abb4" +
                        "43b3b93c05860d3c5f29-1553752071537-693776", headers, parm);
                Map map = JsonUtil.aliToMap(jsonStr);
                if (map.get("code") != null && 200 == (int) map.get("code")) {
                    List<Map> list = JsonUtil.aliToList(JsonUtil.aliToMap(map.get("data").toString()).get("results").toString());
                    if (list != null && !list.isEmpty()) {
                        for (Map job : list) {
                            Map map1 = JsonUtil.aliToMap(job.get("company").toString());
                            if (job.get("welfare") != null) {
                                List<String> fuli = JsonUtil.aliToList(job.get("welfare").toString());//福利
                                String s = "";
                                for (String ss : fuli) {
                                    s += ss;
                                }
                                job.put("welfare", s);//福利待遇
                            }
                            job.put("display", JsonUtil.aliToMap(job.get("city").toString()).get("display"));//地方
                            job.put("workingExp", JsonUtil.aliToMap(job.get("workingExp").toString()).get("name"));//工作经验
                            job.put("company", JsonUtil.aliToMap(map1.get("size").toString()).get("name"));//公司规模
                            job.put("name", map1.get("name"));//公司名
                            job.put("type", JsonUtil.aliToMap(map1.get("type").toString()).get("name"));//性质
                            job.put("eduLevel", JsonUtil.aliToMap(job.get("eduLevel").toString()).get("name"));//学历要求
                            if (zhiLianJobMapper.selectJob(job) > 0) { //已存在直接下一个
                                System.out.println("已存在，正在下一个----------" + i);
                                continue a;
                            }
                            zhiLianJobMapper.insertJob(job);
                            System.out.println("成功获取一条招聘信息");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
        //智联招聘信息
        return "完成";
    }
}
