# Combined Layouts Demo

### Layout Explanations:

 1. LinearLayout:

    * Arranges children in single direction (horizontal/vertical)

    * Uses ```android:orientation``` and ```layout_weight```

    * Example shows horizontal arrangement with weighted distribution

2. RelativeLayout:

    * Positions views relative to parent or other views

    * Uses attributes like ```layout_centerInParent```, ```layout_toRightOf```

    * Allows complex positioning relationships

3. TableLayout:

    * Organizes content into rows and columns

    * Uses ```<TableRow>``` elements for each row

    * ```stretchColumns``` property controls column sizing

4. FrameLayout:

    * Stacks views on top of each other

    * Uses ```layout_gravity``` for positioning

    * Ideal for overlapping views or fragment containers

5. GridLayout:

    * More flexible than TableLayout

    * Explicitly specifies row and column positions

    * Handles complex grid arrangements efficiently

6. AbsoluteLayout (Deprecated):

    * Positions views with exact X/Y coordinates

    * Uses ```layout_x``` and ```layout_y``` attributes

    * Not recommended for modern development

7. ConstraintLayout:

    * Most powerful modern layout

    * Uses constraints to position views

    * Allows complex responsive layouts


### Important Notes:

* ScrollView: Only supports vertical scrolling (use HorizontalScrollView for horizontal)

* Deprecation Warning: AbsoluteLayout is deprecated and should not be used in production

* Performance: ConstraintLayout is preferred for complex layouts

* Best Practice: Use appropriate layout combinations for responsive designs

* Dependencies: ConstraintLayout requires implementation in build.gradle:

    ```implementation 'androidx.constraintlayout:constraintlayout:2.2.1'```

