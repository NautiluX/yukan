# Project YuKan

Find gold in your daily work!

The goal of this project is to provide a kanban board that can be used for both personal and business work. We want to enable the user to show tasks from different origins (aka. backends) which may be located in a protected network due to potential confidentiality of the data in the tasks themselves. Other tasks with less confidentiallity the user may want to store in a location where they can be accessed anytime.

The system shall be accessible for mobile devices as well as any web browser.

## Setup
### Option 1: Using docker-compose
**Recommended for development**

- In root directory: docker-compose up -d
- In *java_backend* directory: mvn tomcat7:deploy
- go to *localhost:3333/backend/setup*
- create a user by executing *docker exec yukan_database_1 /kanbanscripts/create_user.sh -a -u admin -p admin* (see [Create Users](#create-users) for more usage information) containername may need to be adjusted to your setup.
- go to *localhost:3333/backend/migrate* to upgrade to the latest db schema
- go to *localhost:3333*

### Option 2: Without docker
**Recommended for production**
- Install MySql
- Install Tomcat
- Install apache httpd
- In apache, configure virtual host like the following
```
<VirtualHost *:80>
    ServerAdmin webmaster@your.server.org

    DocumentRoot /var/www/html/yukan

    ProxyPass /backend/ http://localhost:8080/backend/
    ProxyPassReverse /backend/ http://localhost:8080/backend/

    ServerName your.server.org
</VirtualHost>
```
- Restart apache
- run the following SQL in MySQL as MySQL root user (change the password of course):
- Adjust *java_backend/src/main/webapp/META-INF/context.xml* to fit your database location and user credentials (primary the following lines)
```
line *url="jdbc:mysql://database:3306/kanban"
username="kanban" password="k4nb4n"
```
- Copy *sbin/config.sh* to *~/.yukan_config.sh* and make it fit your needs
- In *java_backend* directory: run *mvn clean install*
- Copy *java_backend/target/kanban.war* to */var/lib/tomcat8/webapps/backend.war*
- Restart tomcat
- in *sbin* run *setup.sh*
- In a browser, go to your.server.org/backend/setup

## Create Users
Copy example *sbin/config.sh* to *~/.yukan_config.sh* and adjust your configuration inside (if not done already).

### Create User
```
cd sbin
./create_user.sh -u kanban_user -p secret
```

### Create Admin User
```
cd sbin
./create_user.sh -a -u kanban_admin -p topsecret
```

## Features
Backlog managed [here](http://yukan.ntlx.org)

## Board Permissions Concept
- READ - User is allowed to read a board
- CONTRIBUTE - User can drag, create and delete cards
- MANAGE - User can change board lanes, change board permissions

## Admin URLs
- /backend/setup -- initial setup
- /backend/migrate -- upgrade database schema

## Get Involved

You want to stay up to date with this project? Sign up to our mailing list to stay up to date with what's going on. Every change in this project is communicated to this mailing list.

[https://groups.google.com/forum/#!forum/projectyukan]

## License

This software is published under the MIT license, see [https://github.com/NautiluX/yukan/blob/master/LICENSE]
