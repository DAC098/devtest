/*
 * filename:        ParentViewMapperImpl.java
 * author:          David Cathers
 * created:         11/5/2016
 * reference:       Oracle
 * description:     the implementation of the ParentViewMapper interface
 */

package com.build.devtest;

import java.util.ArrayList;
import java.util.List;

public class ParentViewMapperImpl implements ParentViewMapper {

    /*
     * funciton mapRowsToViews
     * takes a list of parents and a list of children and assigns all children
     * to the appropriate parent
     * @params: parentRows [ParentRow List], the list of parents to assign
     *          children
     * @params: childRows [ChildRow List], the list of children to assign
     *          parents
     * @return: [ParentView List], the completed list of parents and children
     */
    public List<ParentView> mapRowsToViews(List<ParentRow> parentRows, List<ChildRow> childRows) {

        // create an empty ParentView List that will be returned when complete
        List<ParentView> rtn = new ArrayList<ParentView>();

        // start cycling thru parentRows
        for(ParentRow pr : parentRows) {

            // get the parent id and convert it to lower case
            String parentID = pr.getParentId().toLowerCase();

            // create an empty ChildView List that will be set to the current
            // parent when all the children have been found
            List<ChildView> kidList = new ArrayList<ChildView>();

            // make a temporary parent view from the current ParentRow
            ParentView parTemp = new ParentView(pr.getFirstName(),pr.getLastName(),pr.getAge(),pr.getParentId(),null);

            // start cycling thru childRows
            for(ChildRow cr : childRows) {

                // get the parentID of the current child and convert it to
                // lowercase
                String kidsParent = cr.getParentId().toLowerCase();

                // if the kids parent id matchs the id of the current parent
                if(kidsParent.equals(parentID)) {

                    // add the child to the current list of children
                    kidList.add(new ChildView(cr.getFirstName(),cr.getLastName(),cr.getAge(),cr.getChildId(),parTemp));
                }
            }

            // assign the current parent the list of children
            parTemp.setChildViews(kidList);

            // add the parent to the return list
            rtn.add(parTemp);
        }

        return rtn;
    }

}
