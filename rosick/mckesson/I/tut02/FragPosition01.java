package rosick.mckesson.I.tut02;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import rosick.LWJGLWindow;
import rosick.jglsdk.framework.Framework;


/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and license terms.
 * 
 * I. The Basics
 * Chapter 2. Playing with Colors
 * http://www.arcsynthesis.org/gltut/Basics/Tutorial%2002.html
 * @author integeruser
 */
public class FragPosition01 extends LWJGLWindow {
	
	public static void main(String[] args) {		
		new FragPosition01().start();
	}
	
	
	private final String TUTORIAL_DATAPATH = "/rosick/mckesson/I/tut02/data/";

	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private final float vertexData[] = {
			0.75f, 0.75f, 0.0f, 1.0f,
			0.75f, -0.75f, 0.0f, 1.0f,
		   -0.75f, -0.75f, 0.0f, 1.0f,
	};
	
	private int theProgram; 
	private int vertexBufferObject;
	private int vao;
	
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private void initializeProgram() {			
		ArrayList<Integer> shaderList = new ArrayList<>();
		shaderList.add(Framework.loadShader(GL_VERTEX_SHADER,	TUTORIAL_DATAPATH + "FragPosition.vert"));
		shaderList.add(Framework.loadShader(GL_FRAGMENT_SHADER, TUTORIAL_DATAPATH + "FragPosition.frag"));

		theProgram = Framework.createProgram(shaderList);
	}
	
	private void initializeVertexBuffer() {
		FloatBuffer vertexDataBuffer = BufferUtils.createFloatBuffer(vertexData.length);
		vertexDataBuffer.put(vertexData);
		vertexDataBuffer.flip();
		
        vertexBufferObject = glGenBuffers();	       
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
	    glBufferData(GL_ARRAY_BUFFER, vertexDataBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	
	@Override
	protected void init() {
		initializeProgram();
		initializeVertexBuffer(); 

		vao = glGenVertexArrays();
		glBindVertexArray(vao);
	}
	
		
	@Override
	protected void display() {	
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);

		glUseProgram(theProgram);

		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);

		glDrawArrays(GL_TRIANGLES, 0, 3);

		glDisableVertexAttribArray(0);
		glUseProgram(0);
	}
}