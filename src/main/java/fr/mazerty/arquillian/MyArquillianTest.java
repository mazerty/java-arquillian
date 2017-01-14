package fr.mazerty.arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.jboss.shrinkwrap.resolver.api.maven.strategy.AcceptAllStrategy;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class MyArquillianTest {

    @Deployment
    public static WebArchive defaultDeployment() {
        return ShrinkWrap.create(MavenImporter.class)
                .loadPomFromFile("pom.xml")
                .importBuildOutput(AcceptAllStrategy.INSTANCE)
                .as(WebArchive.class);
    }

}
