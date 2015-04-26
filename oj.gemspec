
require 'date'

Gem::Specification.new do |s|
  s.name = "oj"
  s.version = "1.0"
  s.authors = "Peter Ohler"
  s.date = Date.today.to_s
  s.email = "peter@ohler.com"
  s.homepage = "http://www.ohler.com/oj"
  s.summary = "A fast JSON parser and serializer."
  s.description = %{The fastest JSON parser and object serializer. }
  s.licenses = ['MIT']

  files = Dir.glob("ext/**/*.{c,java,rb}") +
          Dir.glob("lib/**/*.rb") + 
	  Dir.glob("src/**/*.{java}")

  if RUBY_PLATFORM =~ /java/
    s.platform = "java"
    files << "bin/oj.jar"
  else
    
  end

  s.files = files

  s.add_development_dependency "rake-compiler"

end
