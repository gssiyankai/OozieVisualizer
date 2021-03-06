package oozieviz.workflow.job;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static oozieviz.Constants.JOB_PROPERTIES;
import static oozieviz.ResourceHelper.resourceFile;
import static oozieviz.workflow.job.JobProperties.newJobProperties;
import static org.fest.assertions.Assertions.assertThat;

public class JobPropertiesTest {

    private JobProperties properties;

    @Before
    public void setup() throws Exception {
        properties = newJobProperties(Optional.of(resourceFile(JOB_PROPERTIES)));
    }

    @Test
    public void it_leaves_text_without_token_unchanged() {
        assertThat(properties.replaceTokens("foo")).isEqualTo("foo");
    }

    @Test
    public void it_replaces_unknown_tokens_with_whitespaces() {
        assertThat(properties.replaceTokens("${foo}")).isEmpty();
    }

    @Test
    public void it_replaces_tokens() {
        assertThat(properties.replaceTokens("${sub_workflow_folder}")).isEqualTo("sub-wf");
    }

}
