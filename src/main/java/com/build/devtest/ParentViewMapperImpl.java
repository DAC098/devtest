package com.build.devtest;

import java.util.List;
import java.util.ArrayList;

public class ParentViewMapperImpl implements ParentViewMapper{

  /**
  * mapRowsToViews is a function that iterates through a list of provided parentRows,
  * and creates parentViews for each row. It will iterate through the provided list
  * of childRows, and match the parentId on the childRow with the parentId on the
  * parentRow to create a list of childViews for each particular parent.
  * @param {ParentRow[]} parentRows - list of ParentRow objects containing our
  * parentView data.
  * @param {childRow[]} childRows - list of ChildRow objects containing our
  * childView data.
  * @return {ParentView[]} parentViewsList - list of parentViews.
  */
  public List<ParentView> mapRowsToViews(List<ParentRow> parentRows, List<ChildRow> childRows){

    //create our top level list of parentViews
    List<ParentView> parentViewsList = new ArrayList<ParentView>();

    //iterate through the list of parentRows provided
    for (ParentRow parentRow : parentRows) {

      //create a new parentView using the parentRow data. childViews will be set later
      ParentView newParentView = new ParentView(parentRow.getFirstName(),
        parentRow.getLastName(),
        parentRow.getAge(),
        parentRow.getParentId(),
        new ArrayList<ChildView>()
      );

      //create our second level list for each parentView
      List<ChildView> childViewsList = new ArrayList<ChildView>();

      //iterate through our list of childRows looking for matching parentId's
      for (ChildRow childRow : childRows) {

        //check to see if our parentRow parentId equals our childView parentId (ignoring casing)
        if (childRow.getParentId().equalsIgnoreCase(parentRow.getParentId())) {

          //create a new childView with our childRow data
          ChildView newChildView = new ChildView(childRow.getFirstName(),
            childRow.getLastName(),
            childRow.getAge(),
            childRow.getParentId(),
            newParentView
          );

          //add the new childView to our childViewList
          childViewsList.add(newChildView);
        }
      }

      //set our list of childViews on our newParentView
      newParentView.setChildViews(childViewsList);

      //finally, we add our shiny new parentView to the parentViewList
      parentViewsList.add(newParentView);
    }

    return parentViewsList;
  }

}
