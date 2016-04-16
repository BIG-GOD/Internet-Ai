# Docker 容器部署

ECS上存在的容器部署



## 部署指令

* **nginx_front 容器部署**

  * nginx_front容器基于**Daocloud的nginx_proxy**镜像，内置vim，不支持iptables，提供SSL支持，nobody用户及用户组支持

  * 启动方式

    ```shell
    $ docker start nginx_front
    ```


  * 暂停方式

    ```shell
    $ docker stop nginx_front
    ```

  ​

* **zhao_flask_mysql 容器部署**

  * zhao_flask_mysql容器基于**ubuntu14.04**镜像，内置vim，mysql client端，支持iptables，容器内flask服务器对IP开放为自适应的，无需手动配置ENV，启动时可附加 `python /app/flaskmysql.py`指令，容器在创建时已经与**mysqlserver容器**做好了连接，连接使用的是**Docker_DB**主机名

  - 启动方式

    ```shell
    $ docker start zhao_flask2_mysql
    ```


  - 暂停方式

    ```shell
    $ docker stop zhao_flask2_mysql
    ```

  ​

* **zhao_flask2_mysql 容器部署**

  * 同 zhao_flask_mysql 容器部署

  ​

* **mysqlserver 容器部署**

  * mysqlserver 容器基于**centOS 6**镜像，内置mysql server端，支持iptables，支持vim

  * 容器对**3306**端口做了iptables端口映射，固定映射端口为**32771**，平时不对外开放，仅对开发者需要调试数据库或者需要维护的时候通过iptables开放，正常运行时间mysqlserver容器的端口为动态端口映射。

  * 启动方式

    ```shell
    $ docker start mysqlserver
    ```

  * 暂停方式

    ```shell
    $ docker stop mysqlserver
    ```

​		

