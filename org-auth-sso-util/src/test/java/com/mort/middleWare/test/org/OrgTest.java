package com.mort.middleWare.test.org;

import com.mort.middle.ware.org.entity.OrgTree;
import com.mort.middle.ware.org.service.OrgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context.xml"})
public class OrgTest {
    @Resource
    private OrgService orgService;

    @Test
    public void orgTest(){
        orgService.getAllOrgFormDB();
        orgService.getChildOrg(196);
        orgService.getChildOrg(198);
        orgService.getChildList(196);
        orgService.getChildList(198);
        OrgTree parentOrg = orgService.getParentOrg(196);
        List<OrgTree> parentList = orgService.getParentList(196);
        List<Integer> OrgPath = parentList.stream().map(OrgTree::getId).collect(Collectors.toList());
        List<String> OrgPathName = parentList.stream().map(OrgTree::getName).collect(Collectors.toList());
    }
}
