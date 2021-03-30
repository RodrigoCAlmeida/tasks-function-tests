package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();	
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		//ChromeOptions cap = new ChromeOptions(); 
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.175:4444/wd/hub"), cap);	
		driver.navigate().to("http://192.168.0.175:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(60 , TimeUnit.SECONDS);
		return driver;
	}
	
	@Test	
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = 	acessarAplicacao();
		
		try {
//			driver.navigate().to("http://localhost:8001/tasks/");
			//driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso
			String menssage = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!!!", menssage);
		} finally{
			//fechar o browser
			driver.quit();
		}

	}
	
	@Test	
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = 	acessarAplicacao();
		
		try {
//			driver.navigate().to("http://localhost:8001/tasks/");
		//	driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally{
			//fechar o browser
			driver.quit();
		}

	}
	
	
	@Test	
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = 	acessarAplicacao();
		
		try {
//			driver.navigate().to("http://localhost:8001/tasks/");
			//driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso
			String menssage = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", menssage);
		} finally{
			//fechar o browser
			driver.quit();
		}

	}
	
	@Test	
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = 	acessarAplicacao();
		
		try {
//			driver.navigate().to("http://localhost:8001/tasks/");
		//	driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso
			String menssage = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", menssage);
		} finally{
			//fechar o browser
			driver.quit();
		}

	}
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//inserir tarefa
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
			//remover a Tarefa
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
			message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {			
			//fechar o browser
			driver.quit();
		}
	}
	
	

}
