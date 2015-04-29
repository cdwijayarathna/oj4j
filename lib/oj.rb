require 'oj/bag'
require 'oj/error'
require 'oj/mimic'
require 'oj/saj'
require 'oj/schandler'

module Oj
    if RUBY_PLATFORM =~ /java/
      require File.expand_path('../../bin/oj.jar', __FILE__)

      def self.mask(*args)
        @mask ||= Sequence.new
        @mask.mask(*args)
      end
    end
 end
