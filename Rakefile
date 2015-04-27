require 'ant'

directory "pkg/classes"
MAIN_SRC_DIR = 'ext/main'  
TEST_SRC_DIR = 'ext/test' 
TEST_REPORT_DIR = "pkg/report"
CLASSES_DIR = "pkg/classes"

task :setup do   
  ant.path :id => 'classpath' do  
    fileset :dir => "./bin"
    pathelement :location => File.join(RbConfig::CONFIG["libdir"],"jruby.jar")
  end  
end 

desc "Clean up build artifacts"
task :clean do
  rm_rf "pkg/classes"
  rm_rf "bin/oj.jar"
end

desc "Compile the extension"
task :compile => "pkg/classes" do |t|
  ant.javac :srcdir => "ext", :destdir => t.prerequisites.first,
    :source => "1.5", :target => "1.5", :debug => true,
    :classpath => "${java.class.path}:${sun.boot.class.path}:./bin/junit-4.12.jar:./lib/oj.jar:./bin/ant-junit4.jar:./bin/hamcrest-all-1.3.jar"
end

desc "Build the jar"
task :jar => :compile do
  ant.jar :basedir => "pkg/classes", :destfile => "bin/oj.jar", :includes => "**/*.class"
end

task :run_tests => [:jar,:setup] do  
  ant.mkdir :dir => TEST_REPORT_DIR  
  ant.junit :fork => "yes", :forkmode => "perTest", :printsummary => "yes",  
            :haltonfailure => "no", :failureproperty => "tests.failed" do  
    classpath :refid => 'classpath'
    formatter :type => "xml"  
    batchtest :todir => TEST_REPORT_DIR do  
      fileset :dir => TEST_SRC_DIR, :includes => '**/Test*.java'  
    end  
  end  
  if ant.project.getProperty("tests.failed")  
    ant.junitreport :todir => TEST_REPORT_DIR do  
      fileset :dir => TEST_REPORT_DIR, :includes => "TEST-*.xml"  
      report :todir => "#{TEST_REPORT_DIR}/html"  
    end  
    ant.fail :message => "One or more tests failed. Please check the test report for more info."  
  end  
  puts  
end  

