package com.gazihandemir.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

class SurvivorBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture bird;
    Texture bird2;
    Texture beeRed;
    Texture beeRed2;
    Texture beeGreen;
    Texture beeGreen2;
    Texture beeYellow;
    Texture beeYellow2;
    float birdY = 0;
    float birdX = 0;
    int gameState = 0;
    float velocity = 0;
    float enemiesVelocity =5;
    float gravity = 0.5f;
    //float enemyX = 0;
    int kanatRnd = 0;
    int numberOfEnemies = 4;
    float[] enemyX = new float[numberOfEnemies];
    float distance = 0;
    float[] enemyOffset1 = new float[numberOfEnemies];
    float[] enemyOffset2 = new float[numberOfEnemies];
    float[] enemyOffset3 = new float[numberOfEnemies];
    Random rnd;
    Circle birdcycle;
    Circle[] enemyCircle1;
    Circle[] enemyCircle2;
    Circle[] enemyCircle3;
    ShapeRenderer shapeRenderer;
    int score = 0;
    int enemyScore = 0;
    BitmapFont font,font2,font3;

    @Override
    public void create() {

        batch = new SpriteBatch();
        background = new Texture("Full-Background.png");
        bird = new Texture("mybird.png");
        bird2 = new Texture("mybirdiki.png");
        beeRed = new Texture("redbee2.png");
        beeRed2 = new Texture("redbee1.png");
        beeGreen = new Texture("greenflyone.png");
        beeGreen2 = new Texture("greenflytwo.png");
        beeYellow = new Texture("yellowbee.png");
        beeYellow2 = new Texture("yellowbee2.png");
        birdX = Gdx.graphics.getWidth() / 4;
        birdY = Gdx.graphics.getHeight() / 3;
        distance = Gdx.graphics.getWidth() / 3;
        rnd = new Random();

        birdcycle = new Circle();
        enemyCircle1 = new Circle[numberOfEnemies];
        enemyCircle2 = new Circle[numberOfEnemies];
        enemyCircle3 = new Circle[numberOfEnemies];
        shapeRenderer = new ShapeRenderer();

        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(4);
        font2 = new BitmapFont();
        font2.setColor(Color.BLACK);
        font2.getData().setScale(6);
        font3 = new BitmapFont();
        font3.setColor(Color.RED);
        font3.getData().setScale(8);
        for (int i = 0; i < numberOfEnemies; i++) {
            enemyX[i] = Gdx.graphics.getWidth() - beeRed2.getWidth() / 2 + i * distance;
            enemyOffset1[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyOffset2[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyOffset3[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
            enemyCircle1[i] = new Circle();
            enemyCircle2[i] = new Circle();
            enemyCircle3[i] = new Circle();
        }
        // enemyX = 900;
    }

    @Override
    public void render() {
            //
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (gameState == 1) {


            if (enemyX[enemyScore] < birdX) {
                score++;
                if (enemyScore < numberOfEnemies - 1) {
                    enemyScore++;
                } else {
                    enemyScore = 0;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (enemyX[i] < 0) {
                    enemyX[i] += numberOfEnemies * distance;
                    enemyOffset1[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffset2[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffset3[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
                } else {
                    enemyX[i] -= enemiesVelocity;
                }

                batch.draw(beeRed2, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffset1[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                batch.draw(beeGreen, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffset2[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                batch.draw(beeYellow, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffset3[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                enemyCircle1[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffset1[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
                enemyCircle2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffset2[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
                enemyCircle3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30,  Gdx.graphics.getHeight()/2 + enemyOffset3[i] + Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);

            }



       /*     kanatRnd = rnd.nextInt(2);
            if (kanatRnd == 0){
                batch.draw(beeRed2, enemyX, 550, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                batch.draw(beeGreen, enemyX, 350, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                batch.draw(beeYellow2, enemyX, 150, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
            }else if ( kanatRnd == 1){
                batch.draw(beeGreen2, enemyX, 350, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                batch.draw(beeYellow, enemyX, 150, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                batch.draw(beeRed, enemyX, 550, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

            }*/


            //enemyX = enemyX -1;

            if (Gdx.input.isTouched()) {
                velocity = -10;
            }
            if (birdY > 0 && birdY < Gdx.graphics.getHeight()- Gdx.graphics.getHeight() / 10) {
                velocity = velocity + gravity;
                birdY = birdY - velocity;
                /*System.out.println("Agravity = " + gravity);
                System.out.println("Avelocity = " + velocity);
                System.out.println("Abirdy = " + birdY);
               */
                System.out.println("Alii y : "+Gdx.graphics.getHeight());
                System.out.println("Alii x : "+Gdx.graphics.getWidth());
               // System.out.println("Alii biry : "+birdY);
            }
                else {
                gameState = 2;
            }


        } else if (gameState == 0) {
            if (Gdx.input.isTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {
            font2.draw(batch,"Game Over! Tap To Play Again!",100,Gdx.graphics.getHeight() / 2);
            font3.draw(batch,"SCORE : "+score,850,Gdx.graphics.getHeight() / 4);
            if (Gdx.input.isTouched()) {
                gameState = 1;
                birdY = Gdx.graphics.getHeight() / 3;
                for (int i = 0; i < numberOfEnemies; i++) {
                    enemyX[i] = Gdx.graphics.getWidth() - beeRed2.getWidth() / 2 + i * distance;
                    enemyOffset1[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffset2[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyOffset3[i] = ((rnd.nextFloat()) - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    enemyCircle1[i] = new Circle();
                    enemyCircle2[i] = new Circle();
                    enemyCircle3[i] = new Circle();
                }
                velocity = 0;
                enemyScore = 0;
                score = 0;
            }

        }

        if (Gdx.input.isTouched()) {
            batch.draw(bird2, birdX, birdY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
        } else {
            batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
        }
        font.draw(batch, String.valueOf(score), 100, 200);
        batch.end();

        //birdcycle.set(birdX, birdY, Gdx.graphics.getWidth() / 30);
        birdcycle.set(birdX + Gdx.graphics.getWidth() / 30, birdY + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
      /*  shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(birdcycle.x + Gdx.graphics.getWidth() / 30, birdcycle.y + Gdx.graphics.getHeight() / 20, birdcycle.radius);*/

        for (int i = 0; i < numberOfEnemies; i++) {
            /*shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffset1[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
            shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffset2[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
            shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffset3[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);*/
            if (Intersector.overlaps(birdcycle, enemyCircle1[i]) || Intersector.overlaps(birdcycle, enemyCircle2[i]) || Intersector.overlaps(birdcycle, enemyCircle3[i])) {
                gameState = 2;
            }


        }
        // shapeRenderer.end();
    }

    @Override
    public void dispose() {

    }
}
