package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();	
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
		return driver;
	}
	
	@Test	
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = 	acessarAplicacao();
		
		try {
			driver.navigate().to("http://localhost:8001/tasks/");
			driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//Escrever a descri��o
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
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = 	acessarAplicacao();
		
		try {
			driver.navigate().to("http://localhost:8001/tasks/");
			driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
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
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = 	acessarAplicacao();
		
		try {
			driver.navigate().to("http://localhost:8001/tasks/");
			driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//Escrever a descri��o
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
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = 	acessarAplicacao();
		
		try {
			driver.navigate().to("http://localhost:8001/tasks/");
			driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//Escrever a descri��o
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
	
	

}