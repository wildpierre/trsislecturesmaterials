/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.Application;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.security.Principal;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SchoolRestServiceTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    //private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
    //        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Test
    public void testPublicRestSchools() throws Exception {

        mockMvc.perform(get("/public/rest/schools")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].number", is(1)))
                .andExpect(jsonPath("$[0].name", is("Факультет 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].number", is(2)))
                .andExpect(jsonPath("$[1].name", is("Факультет 2")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].number", is(3)))
                .andExpect(jsonPath("$[2].name", is("Факультет 3")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].number", is(4)))
                .andExpect(jsonPath("$[3].name", is("Факультет 4")))
                .andExpect(jsonPath("$[4].id", is(5)))
                .andExpect(jsonPath("$[4].number", is(5)))
                .andExpect(jsonPath("$[4].name", is("Факультет 5")));
    }

    @Test
    public void testRestSchoolsAddDelete() throws Exception {

        Principal principal = createMockAdminPrincipal();

        mockMvc.perform(post("/public/rest/schools/10/mockschool")
                .accept(MediaType.APPLICATION_JSON)
                .principal(principal))
                .andExpect(status().isOk());

        mockMvc.perform(get("/public/rest/schools"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[5].id", is(6)))
                .andExpect(jsonPath("$[5].number", is(10)))
                .andExpect(jsonPath("$[5].name", is("mockschool")));

        mockMvc.perform(delete("/public/rest/schools/6").accept(MediaType.APPLICATION_JSON)
                .principal(principal))
                .andExpect(status().isOk());

        mockMvc.perform(get("/public/rest/schools"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(5)));

    }

    @Test
    public void testRestSchoolsDeleteNonExisting() throws Exception {
        mockMvc.perform(delete("/public/rest/schools/10")
                .accept(MediaType.APPLICATION_JSON)
                .principal(createMockAdminPrincipal()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRestSchoolsDeleteNonExistingUnauthorized() throws Exception {
        mockMvc.perform(delete("/public/rest/schools/10")
                .accept(MediaType.APPLICATION_JSON)
                .principal(createMockGuestPrincipal()))
                .andExpect(status().isForbidden());
    }

    private Principal createMockAdminPrincipal() {
        return createMockPrincipal("admin", "ADMIN_USER");
    }

    private Principal createMockGuestPrincipal() {
        return createMockPrincipal("guest", "GUEST_USER");
    }

    private Principal createMockPrincipal(String user, String authority) {
        User mockPrincipal = Mockito.mock(User.class);
        
        Mockito.when(mockPrincipal.getUsername())
                .thenReturn(user);

        Mockito.when(mockPrincipal.getAuthorities())
                .thenReturn(Collections.singleton((GrantedAuthority) () -> authority));

        Authentication principal = (Authentication) Mockito.mock(Authentication.class);
        
        Mockito.when(principal.getPrincipal())
                .thenReturn(mockPrincipal);

        return principal;
    }

}
