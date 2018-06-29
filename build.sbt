name := "baseball"

version := "1.0"

lazy val `baseball` = (project in file(".")).enablePlugins(PlayJava, PlayEbean)


resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "webjars" at "http://webjars.github.com/m2"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  "com.github.twitter" % "bootstrap" % "2.0.2",
  "org.webjars" % "bootstrap" % "3.0.0",
  "org.webjars" %% "webjars-play" % "2.4.0",
  "org.postgresql" % "postgresql" % "9.4.1208.jre7"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

      
