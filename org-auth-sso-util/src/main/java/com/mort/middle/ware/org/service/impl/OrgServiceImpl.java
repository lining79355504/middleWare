package com.mort.middle.ware.org.service.impl;

import com.mort.middle.ware.org.entity.OrgTree;
import com.mort.middle.ware.org.mapper.OrgGroupMapper;
import com.mort.middle.ware.org.mapper.OrgMemberMapper;
import com.mort.middle.ware.org.model.OrgGroup;
import com.mort.middle.ware.org.service.OrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrgServiceImpl implements OrgService, InitializingBean {

     private static final Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);

    @Resource
    private OrgGroupMapper orgGroupMapper;

    @Resource
    private OrgMemberMapper orgMemberMapper;

    @Override
    public OrgTree getLimitedOrg(Integer id) {
        OrgTree orgTree = getAllOrgFormDB();
        OrgTree result = new OrgTree();
        return getLimitedOrg(orgTree, id, result);
    }

    public OrgTree getLimitedOrg(OrgTree orgTree, Integer id, OrgTree result) {
        if (null == orgTree) {
            return result;
        }

        result.setId(orgTree.getId());
        result.setName(orgTree.getName());
        result.setChildren(orgTree.getChildren());
        for (OrgTree child : orgTree.getChildren()) {
            OrgTree nodeOrg = getLimitedOrg(child, id, result.getChildren().get(0));
            if (nodeOrg.getId().equals(id)) {
                return result;
            } else {
                result.getChildren().remove(0);
            }

        }
        return result;
    }

    @Override
    public OrgTree getAllOrgFormDB() {
        List<OrgGroup> orgGroups = orgGroupMapper.selectAll(2000);
        OrgTree orgTree = new OrgTree(0,"总办",new ArrayList<>());
        buildOrgFormDB(orgTree, orgGroups);
        return orgTree;
    }

    private void buildOrgFormDB(OrgTree orgTree, List<OrgGroup> orgGroups) {
        if (null == orgTree) {
            return;
        }

        for (OrgGroup orgGroup : orgGroups) {
            if(null == orgGroup.getParentId()){
                continue;
            }
            if (orgGroup.getParentId().equals(orgTree.getId())) {
                orgTree.getChildren().add(new OrgTree(orgGroup.getId(), orgGroup.getName(), new ArrayList<>()));
                for (OrgTree treeChild : orgTree.getChildren()) {
                    buildOrgFormDB(treeChild, orgGroups);
                }
            }
        }
    }


    @Override
    public OrgTree getChildOrg(Integer id) {
        OrgTree orgTree = getAllOrgFormDB();
        return getChildOrg(orgTree, id);
    }

    private OrgTree getChildOrg(OrgTree orgTree, Integer id) {
        if (null == orgTree) {
            return null;
        }
        if (id.equals(orgTree.getId())) {
            return orgTree;
        }

        for (OrgTree child : orgTree.getChildren()) {
            OrgTree childOrg = getChildOrg(child, id);
            if (null != childOrg) {
                return childOrg;
            }
        }
        return null;
    }

    @Override
    public List<OrgTree> getChildList(Integer id) {
        OrgTree childOrg = getChildOrg(id);
        return getChildList(childOrg);
    }

    public List<OrgTree> getChildList(OrgTree orgTree) {
        if (null == orgTree) {
            return null;
        }
        List<OrgTree> result = new ArrayList<>();
        result.add(new OrgTree(orgTree.getId(), orgTree.getName(), new ArrayList<>()));
        for (OrgTree child : orgTree.getChildren()) {
            List<OrgTree> childList = getChildList(child);
            if (null != childList) {
                result.addAll(childList);
            }
        }
        return result;
    }

    @Override
    public OrgTree getParentOrg(Integer id) {
        OrgTree orgTree = getAllOrgFormDB();
        OrgTree result = new OrgTree();
        return getParentOrg(orgTree, id, result);
    }

    public OrgTree getParentOrg(OrgTree orgTree, Integer id, OrgTree result) {
        if (null == orgTree) {
            result.getChildren().remove(0);
            return null;
        }
        result.setId(orgTree.getId());
        result.setName(orgTree.getName());
        result.setChildren(new ArrayList<>());
        result.getChildren().add(new OrgTree());
        if (orgTree.getId().equals(id)) {
            result.getChildren().remove(0);
            return result;
        }
        for (OrgTree child : orgTree.getChildren()) {
            OrgTree tree = getParentOrg(child, id, result.getChildren().get(0));
            if (null == tree) {
                result.getChildren().remove(0);
            }
            return result;
        }
        return result;
    }


    @Override
    public List<OrgTree> getParentList(Integer id) {
        OrgTree parentOrg = getParentOrg(id);
        return getParentList(parentOrg);
    }

    public List<OrgTree> getParentList(OrgTree orgTree) {
        if (null == orgTree) {
            return null;
        }
        List<OrgTree> result = new ArrayList<>();
        result.add(new OrgTree(orgTree.getId(), orgTree.getName(), new ArrayList<>()));
        for (OrgTree child : orgTree.getChildren()) {
            List<OrgTree> parentList = getParentList(child);
            if (!CollectionUtils.isEmpty(parentList)) {
                result.addAll(parentList);
            }
        }

        return result;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
