package coursier.install

import coursier.core.Repository
import dataclass.data

import java.nio.charset.StandardCharsets

@data class AppInfo(
  appDescriptor: AppDescriptor,
  appDescriptorBytes: Array[Byte],
  source: Source,
  sourceBytes: Array[Byte],
  overrideVersionOpt: Option[String] = None
) {
  def overrideVersion(version: String): AppInfo =
    withAppDescriptor(appDescriptor.overrideVersion(version))
      .withOverrideVersionOpt(Some(version))
  def overrideVersion(versionOpt: Option[String]): AppInfo =
    versionOpt.fold(this)(overrideVersion(_))
  def withRepositories(repositories: Seq[Repository]): AppInfo = {
    val appDescWithRepositories =
      this.appDescriptor.withRepositories(repositories)

    this.withAppDescriptor(
      appDescWithRepositories
    )
  }
}
