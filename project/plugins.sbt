logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "1.0.0")
