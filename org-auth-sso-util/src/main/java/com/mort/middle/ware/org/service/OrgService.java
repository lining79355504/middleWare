package com.mort.middle.ware.org.service;

import com.mort.middle.ware.org.entity.OrgTree;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrgService {

    /**
     * 获取课时的组织节点（下属全部组织节点，上级单线树）
     * @param id
     * @return
     */
    OrgTree getLimitedOrg(Integer id);

    /**
     * 从DB获取全部组织节点
     * @return
     */
    OrgTree getAllOrgFormDB();

    OrgTree getChildOrg(Integer id);

    /**
     * 返回的orgTree child全部为空 从第一层按顺序放入返回的list
     * @return
     */
    List<OrgTree> getParentList(Integer id);

    /**
     * 返回的orgTree child全部为空
     * @return
     */
    List<OrgTree> getChildList(Integer id);

    /**
     * 显示根节点到自身的路径 不显示多余层级的节点
     * @param id
     * @return
     */
    OrgTree getParentOrg(Integer id);


}
