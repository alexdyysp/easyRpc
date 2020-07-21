# easyRpc
模仿dubbo实现的简易RPC框架

- Provider模块
  - 提供API、实现API、暴露(启动tomcat, NettyServer)、服务本地注册、服务注册中心注册
- Consumer模块
  - 拿接口名从注册中心获取服务地址、调用
- Registry模块
  - 保存服务配置信息（服务名：List<URL>）
- RpcProtocol模块
  - 基于tomcat的httpprotocol、基于netty的dubboprotocol
- Framework模块
  - 框架实现

内嵌tomcat

java动态代理实现代理对象

消费者调用服务需要负载均衡：随机、轮询、hash、最小活跃次数
