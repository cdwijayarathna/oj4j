# oj4j

This repo contains the source of JRuby implementation of Ruby 'oj' gem.
After finishing, this will be integrated with https://github.com/ohler55/oj.

##To use the gem

rake jar

gem build oj.gemspec

gem install oj

## Unit testing

Copy junit.jar and ant-junit4.jar in to lib folder.

rake run_tests

