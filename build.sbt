name := "LeagueManager"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "postgresql" % "postgresql" % "8.4-702.jdbc4"
)     

play.Project.playJavaSettings
