# oj4j

This repo contains the source of JRuby implementation of Ruby 'oj' gem.
After finishing, this will be integrated with https://github.com/ohler55/oj.

##To use the gem

rake jar

gem build oj.gemspec

gem install oj

## Unit testing

Copy junit.jar, ant-junit4.jar, jruby-complete-1.7.4.jar and hamcrest-all-1.3.jar in to bin  folder.

rake run_tests

