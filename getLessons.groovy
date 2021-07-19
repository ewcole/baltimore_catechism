#! /usr/bin/groovy
import groovy.json.*;
def catFile = new File('baltimore_catechism.json');
assert catFile.exists();

def cat = new JsonSlurper().parse(catFile);

cat.lessons.each {
  lesson ->
  println "${lesson.number},${lesson.ordinal},${lesson.title} "
}