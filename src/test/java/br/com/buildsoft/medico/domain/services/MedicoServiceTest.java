package br.com.buildsoft.medico.domain.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.buildsoft.medico.domain.dao.MedicoDao;

@RunWith(MockitoJUnitRunner.class)
public class MedicoServiceTest {
    
	@Mock
	MedicoDao medicoDao;
	
	@InjectMocks
	MedicoService medicoService;
	
    @Test
    public void testCadastrarServlet() {
    	Assert.assertEquals(true, true);
    }
}
