# Flow API

Flow API is a Java API which is aimed at simplifying conversion of parametrised objects and transfer of said objects
across network and/or abstract coordinate system. <br>
It contains a range of utilities which help with most common problems when it comes to handling big amounts of similar
data.

This project started out as a part of [Open Mod Loader](https://github.com/OpenModLoader/OpenModLoader) but OML
sadly died out due to lack of support from Mojang to developers dependant on their code apart from MCP and FML teams
who feel overly protective of their work. As I could at the time see this code can be used for many purposes much
bigger than simple power/item/fluid pipes implemented by many Minecraft mods, I decided to continue working
on it even though I will likely never use it myself because all of my project which could've benefited from this API
are written in languages other than Java.

At this point, this code is so flexible and abstract I can hardly concentrate to write thorough enough documentation
for it which makes it a really long and painful process (but I'm slowly working on it). It can handle everything I can think
of and I'm sure I haven't thought of everything (nor will in foreseeable future) so further development is highly
dependant on the community.

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

## How stable is it?

As I never really got a chance to write something which could test this API properly it is uncertain. I can hardly see
anything going wrong as the code is very generic and simple, but keep in mind it hasn't been tested enough to even be
considered for a beta stage. Any input is appreciated.

## License

This project is released under MIT License copy of which can be found in the root of this repository.
