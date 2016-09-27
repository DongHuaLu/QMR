package com.game.map.message{
	import com.game.map.bean.MonsterInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围怪物
	 */
	public class ResRoundMonsterMessage extends Message {
	
		//周围怪物信息
		private var _monster: MonsterInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//周围怪物信息
			writeBean(_monster);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//周围怪物信息
			_monster = readBean(MonsterInfo) as MonsterInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101102;
		}
		
		/**
		 * get 周围怪物信息
		 * @return 
		 */
		public function get monster(): MonsterInfo{
			return _monster;
		}
		
		/**
		 * set 周围怪物信息
		 */
		public function set monster(value: MonsterInfo): void{
			this._monster = value;
		}
		
	}
}