# Flow API

[![Build Status](https://travis-ci.org/Caellian/FlowAPI.svg?branch=master)](https://travis-ci.org/Caellian/FlowAPI)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3420e12cb3cb4f978c52989e6e6742c8)](https://www.codacy.com/app/Caellian/FlowAPI?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Caellian/FlowAPI&amp;utm_campaign=Badge_Grade)
[![Download](https://api.bintray.com/packages/caellian/caellian/FlowAPI/images/download.svg)](https://bintray.com/caellian/caellian/FlowAPI/_latestVersion)
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE.md)


Flow API is a Java API which is aimed at simplifying conversion of parametrised objects and transfer of said objects
across network and/or abstract coordinate system. <br>
It contains a range of utilities which help with most common problems when it comes to handling big amounts of similar
data.

This project started out as a Power API for [Open Mod Loader](https://github.com/OpenModLoader/OpenModLoader) but OML
sadly died out. As I could at the time see this code can be used for many purposes much bigger than simple
power/item/fluid pipes implemented by many Minecraft mods, I decided to continue working on it even though I will
likely never use it myself because all of my project which could've benefited from this API are written in languages
other than Java.

It can handle everything I can think of and I'm sure I haven't thought of everything (nor will in foreseeable future)
so further development is highly dependant on the community.

## What does it do?

- Data conversion (e.g. transformers, conversion from one form of energy to another)
    - Flexible conversion management:
        - Blacklist incompatible forms of data
        - Whitelist compatible forms of data
        - Data groups which work as whitelists applied to all objects within a group
        - Indirect conversion (A -> B -> C) with variable number of steps
- Data transfer management
    - Supports abstract space data containers (e.g. Game Worlds)
    - Custom network management
        - Every network can have it's own behaviour
        - Allows optimisation of game mechanic code (not all network components need to be updated if it isn't necessary).

## Including FlowAPI in your projects
### Gradle
Add jCenter to repositories closure in build.gradle:
```Groovy
jcenter()
```
After adding following statement to dependencies closure you are good to go:
```Groovy
compile 'hr.caellian:flow:1.1.+'
```

### Maven
Maven settings:
```xml
<settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd'
xmlns='http://maven.apache.org/SETTINGS/1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
  <profiles>
  	<profile>
  		<repositories>
  			<repository>
  				<snapshots>
  					<enabled>false</enabled>
  				</snapshots>
  				<id>bintray-caellian-caellian</id>
  				<name>bintray</name>
  				<url>http://dl.bintray.com/caellian/caellian</url>
  			</repository>
  		</repositories>
  		<pluginRepositories>
  			<pluginRepository>
  				<snapshots>
  					<enabled>false</enabled>
  				</snapshots>
  				<id>bintray-caellian-caellian</id>
  				<name>bintray-plugins</name>
  				<url>http://dl.bintray.com/caellian/caellian</url>
  			</pluginRepository>
  		</pluginRepositories>
  		<id>bintray</id>
  	</profile>
  </profiles>
  <activeProfiles>
  	<activeProfile>bintray</activeProfile>
  </activeProfiles>
</settings>
```
Add this dependency:
```xml
<dependency>
	<groupId>hr.caellian</groupId>
	<artifactId>flow</artifactId>
	<version>[1.1.0,1.2.0)</version>
</dependency>
```

## How stable is it?

As I never really got a chance to write something which could test this API properly it is uncertain. I can hardly see
anything going wrong as the code is very generic and simple, but keep in mind it hasn't been tested enough to even be
considered for a beta stage. Any input is appreciated.

## Dependencies

None. All code written here was written specifically for this API in way which would benefit it the most. There probably
is some code I could've used from existing libraries (like code for pairs), but I feel it would make this API much
bulkier than it has to be.

## License

This project is released under MIT License copy of which can be found in the root of this repository.
