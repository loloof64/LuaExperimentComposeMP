# Lua experiment

A simple experiment with [Lua language](https://www.lua.org/) in Jetpack Compose Multiplatform.

This is a parser for a tiny subset of the Lua language, where :
* strings have been removed
* floats have been removed
* hexadecimal values have been removed
* tables have been removed
* loop instructions have been removed
* functions have been removed

## Credits

### Images

#### [SvgRepo](https://www.svgrepo.com/)

Some images have been downloaded from SvgRepo and converted with [SvgToCompose](https://www.composables.com/svgtocompose).

* save => https://www.svgrepo.com/svg/512798/save-item-1411
* open => https://www.svgrepo.com/svg/501288/open-folder
* execute => https://www.svgrepo.com/svg/366123/execute

### Lua Antlr4 grammar

Imported Lua Antlr4 grammar from [Official Antlr4 examples : Lua](https://github.com/antlr/grammars-v4/tree/master/lua)
(Commit 753536777d827ccc0c9b108531ea67375c2039ac)

Also using lexer from [official antlr4 lua example repository](https://github.com/antlr/grammars-v4/blob/master/lua/Java/LuaLexerBase.java)

Typically, as a developper, you may want to set up Antlr4 in your environment and run command
```
antlr4 -o classes Lua.g4
```