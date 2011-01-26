import sbt._
import com.twitter.sbt.StandardProject

class QuerulousProject(info: ProjectInfo) extends StandardProject(info) {

  val specs     = "org.scala-tools.testing" % "specs_2.8.1" % "1.6.7.2"
  val configgy  = "net.lag" % "configgy" % "2.0.2"
  val dbcp      = "commons-dbcp" % "commons-dbcp" % "1.2.2"
  val hamcrest  = "org.hamcrest" % "hamcrest-all" % "1.1"
  val jmock     = "org.jmock" % "jmock" % "2.4.0"
  val pool      = "commons-pool" % "commons-pool" % "1.3"
  val xrayspecs = "com.twitter" % "xrayspecs_2.8.0" % "2.1.1"
  val hsqldb    = "hsqldb"  % "hsqldb" % "1.8.0.7"

  val ScalaToolsSnap = ScalaToolsSnapshots
  val bumSnapsRepo = "Bum Networks Snapshots Repository" at "http://repo.bumnetworks.com/snapshots/"
  val twttr = "Twitter Maven Repo" at "http://maven.twttr.com/"
  val mavenLocal = "Local Mavem" at "file://" + Path.userHome + "/.m2/repository"

  override def pomExtra =
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        <distribution>repo</distribution>
      </license>
    </licenses>

// TODO: this repo does not exist, and this fork couldn't be published there anyway - moving on...
//  val publishTo = Resolver.sftp("green.lag.net", "green.lag.net", "/web/repo")

  override def packageSrcJar= defaultJarPath("-sources.jar")
  val sourceArtifact = Artifact.sources(artifactID)
  override def packageToPublishActions = super.packageToPublishActions ++ Seq(packageSrc)

  val publishTo = Resolver.sftp("repo.novus.com", "repo.novus.com", "/nv/repo/%s".format(
    if (projectVersion.value.toString.endsWith("-SNAPSHOT")) "snapshots"
    else "releases"
  )) as (System.getProperty("user.name"))

}
