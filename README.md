# Multiplexscrollrecyclerview
horizontal and vertical scroll
    
    "
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) recyclerView.getParent().getParent();
                coordinatorLayout.onStartNestedScroll(recyclerView, recyclerView, 2);
            }
        });
    "
#Resut
![ScreenShot](https://github.com/sangcomz/Multiplexscrollrecyclerview/blob/master/image/image.png)
