package com.me.tdef.Levels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.tdef.CombatHandler;
import com.me.tdef.EntityPhysics;
import com.me.tdef.Constants;
import com.me.tdef.GraphicalUI;
import com.me.tdef.Entities.Enemy;
import com.me.tdef.Entities.EnemySpawner;
import com.me.tdef.Entities.Projectile;
import com.me.tdef.Entities.SelectionTool;
import com.me.tdef.Entities.Tower;
import com.me.tdef.Entities.TowerBehaviour;

/**
 * Holds all game objects and logic.
 *
 */
public class GameLevel {
	private EnemySpawner enemySpawner;
	private Array<Enemy> enemies;
	public Array<Tower> towers;
	
	private TowerBehaviour towerBehaviour;
	
	private GameMap map;
	
	private GraphicalUI ui;
	
	private SelectionTool selectTool;
	
	private int touchCounter;
	
	private final int towerPrice = 5;
	
	private Vector2 buildLocation = new Vector2();
	
	private OrthographicCamera camera;
	
	
	/**
	 * Initializes the level.
	 */
	public void create(OrthographicCamera camera){
		
		towers = new Array<Tower>();
		
		towerBehaviour = new TowerBehaviour();
	
		map = new GameMap(1540, 900f);
		map.loadContent();
		map.createMap(Constants.map1Data);
		
		this.camera = camera;
		
		//camera.position.x = 0;
		//camera.position.y = 0;
		
		enemySpawner = new EnemySpawner(map.getTileArray()[0][0].getPosition(), 90);	
		enemies = enemySpawner.spawnedEnemies;
	
		Projectile.loadSpriteSheet(Constants.FireBoltTextureAsset);
		Enemy.loadSpriteSheet(Constants.ZombieSpriteSheetAsset);
		Tower.loadSpriteSheet(Constants.TowerTextureAsset);

		ui = new GraphicalUI();
		ui.create();
		
		selectTool = new SelectionTool();
		
		touchCounter = 0;
		
		CombatHandler.instance().setTotalCredits(10);
	}
	
	/**
	 * Main update method. Holds all game logic.
	 */
	public void update(float deltaTime){
		
		enemySpawner.Update(deltaTime);
		
		for(Enemy e : enemies){
			e.update(deltaTime);
			EntityPhysics.instance().apply(e);
			map.GuideEnemy(e);
		}
		
		for(Tower t : towers){
			t.update(deltaTime);
			towerBehaviour.apply(t, enemies);
			t.updateProjectiles(deltaTime);
			EntityPhysics.instance().apply(t);
			t.cleanProjectiles(map.getMapWidth(), map.getMapHeight());
		}
		
		CombatHandler.instance().update(towers, enemies, deltaTime);
		
		ui.updateCredits(CombatHandler.instance().getTotalCredits());
	}
	
	/**
	 * Main draw method.
	 */
	public void render(SpriteBatch batch, float deltaTime){
		map.draw(batch);
		
		for(Enemy e : enemies){
			e.draw(batch);
		}
		
		for(Tower t : towers){
			t.draw(batch);			
		}
		
		selectTool.draw(batch);
		
		ui.renderFloatingTexts(batch, deltaTime);
	}
	
	/**
	 * Renders the graphical user interface.
	 */
	public void renderUI() {
		ui.render();
	}
	
	public void handleInput(Input input, float zoom) {
		float x = input.getX() + (camera.position.x / zoom) - Gdx.graphics.getWidth() / 2;
		float y = input.getY() - (camera.position.y / zoom) + Gdx.graphics.getHeight() / 2;
		
		if(input.isTouched()) {
			camera.position.x -= input.getDeltaX();
			camera.position.y += input.getDeltaY();
		}
		
		for(int i = 0; i < map.getTileRowCount(); i++)
			for(int j = 0; j < map.getTileColumnCount(); j++)
			{
				if(map.getTileArray()[i][j] != null && map.getTileArray()[i][j].containsPoint(x * zoom, Gdx.graphics.getHeight() * zoom - y * zoom)) {
					
					selectTool.selectTile(map.getTileArray()[i][j], x * zoom, Gdx.graphics.getHeight() * zoom - y * zoom);
					
					if(selectTool.getSelectedTile() != null && 
							selectTool.getSelectedTile() == map.getTileArray()[i][j] && Gdx.input.justTouched()) {
						touchCounter++;
							if(touchCounter > 1 && selectTool.getPreviousTile().containsPoint(x * zoom, Gdx.graphics.getHeight() * zoom - y * zoom)) {
								buildTower(i, j, selectTool.getSelectedSection());
								touchCounter = 0;
							}
					}
				}
			}	
	}
	
	public void dispose() {
		map.dispose();
		ui.dispose();
		selectTool.dispose();
	}
	
	private void buildTower(int x, int y, int section) {
		if(!map.getTileArray()[x][y].isOccupied(section) && map.getTileArray()[x][y].canBuild() && CombatHandler.instance().getTotalCredits() >= towerPrice) {
			
			buildLocation.x = map.getTileArray()[x][y].getSectionPosition(section).x + map.getTileArray()[x][y].getSectionWidth() / 2;
			buildLocation.y = map.getTileArray()[x][y].getSectionPosition(section).y + map.getTileArray()[x][y].getSectionHeight() / 2;
			
			Tower t = new Tower(new int[]{0}, buildLocation);
			towers.add(t);
			map.getTileArray()[x][y].setOccupied(true, section);
			CombatHandler.instance().setTotalCredits(CombatHandler.instance().getTotalCredits() - towerPrice);
		}
	}


}
