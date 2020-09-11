## 笔记


MVP：mvp 解藕 View 和 Model 层之间的联系，View 和 P 层采用接口形式，拿到的是对象引用
MVVM：View 与 ViewModel 可双向感知，dataBinding


### 服务器地址
https://course.api.cniao5.com

appId:tcvEYULWEc
appKey:J#y9sJesv*5HmqLqEV1yUPYfpH$pHx$!


### 项目架构：

> 网络封装

- 异常处理、请求管理、生命周期
- 通用性、数据回调切换

**okHttp 基本封装**

- 构建 client
- 配置参数
- 构建 request
- 执行请求：call.enqueue/execute

优化点：

- 抽象接口封装
- 配置网络日志工具、重试机制
- 管理请求：cancel/cancelAll