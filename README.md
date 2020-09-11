# cainiao
初始化

### ViewModel 创建方式：

```
    // 方式一：by lazy
    val homeViewModel1: HomeViewModel by ViewModelLazy<HomeViewModel>(HomeViewModel::class,
        { viewModelStore }, { defaultViewModelProviderFactory })

    // 方式二 :
    val homeViewModel2: HomeViewModel by viewModels<HomeViewModel> { defaultViewModelProviderFactory }

    // 方式三 :手动创建
    private lateinit var homeViewModel: HomeViewModel

    // lifecycle 2.0 + 版本方式获得 view model
    homeViewModel = ViewModelProvider(viewModelStore,
           defaultViewModelProviderFactory)
           .get(HomeViewModel::class.java)
```

