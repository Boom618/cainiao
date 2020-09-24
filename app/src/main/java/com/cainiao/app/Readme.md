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

### Retrofit 整合协程和 LiveData

1. OkHttp 请求支持 liveData 的 Ktx 扩展
2. okhttp 支持协程的 ktx 扩展
3. retrofit 支持协程的封装
   1. retrofit 配置 okhttpClient
   2. builder 添加 gson 适配
   3. 添加 livedata 的 adapter

### Room 数据库

使用： 3 步
- @Entity 数据表实体类
- @Dao 用于操作数据表的Dao接口
    - @insert 新增
    - @update
    - @delete
    - @query --- sqlite
- @Database 数据库的抽象类

> 其他注解

1. @ignore 注解忽略对应字段，在数据表中的映射
2. @index 索引字段标记
3. **@primaryKey 数据表的主键**
4. @ForeignKey 数据表的外键,确定entry表之间关系
5. @ColumnInfo 用于数据表字段的映射定义
6. @Embedded 用于数据表字段为外部对象的修饰，不需要那个对象也是@entity
7. @Relation 用于多表关联
8. @Transaction 用于数据库的事务操作标记
9. @DatabaseView 创建虚拟数据表，而非数据库中实际的表数据，可以是多个表的部分拼接的，提高复用
10. @TypeConverter 用于适配转换

> 进阶使用

- 嵌套类：
@Embedded:将额外对象的内容字段，添加到当前entry的表内，所以，其字段不可与当前entry字段重复


- 多表联查
1. sql语句返回数据，构建临时bean对象
2. databaseView 需要在 dataBase 的抽象类中 @database 的 views添加，而后可用于在@query中使用

- 升降级  addMigrations，可多个版本迁移处理

### 依赖注入框架 Koin

> 添加 Koin

- 添加插件
```
classpath "org.koin:koin-gradle-plugin:$koin_version"
```
- 应用插件
```
apply plugin: 'koin'
```
- 添加依赖
```
//koin for Androidx
implementation "org.koin:koin-androidx-scope:$koin_version"
implementation "org.koin:koin-androidx-viewmodel:$koin_version"
implementation "org.koin:koin-androidx-fragment:$koin_version"
implementation "org.koin:koin-androidx-ext:$koin_version"

testImplementation "org.koin:koin-test:$koin_version"
```

> 使用 Koin

- 1. 业务`class`的创建
- 2. 定义`koin`的容器模块`module`，内部声明类的创建（单例、工厂等）
- 3. 在`Application`调用`stratKoin`内部初始化`modules`

### 模块化、组件化、插件化

1. 模块化：功能、控件、View 模块化处理
2. 组件化：一个 Apk 分成不同的组件，更高级的 模块化。
3. 插件化：是运行期技术、与热更新、热修复紧密关联

### 模块化划分

