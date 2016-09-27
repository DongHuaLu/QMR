package com.game.player.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家外观展示信息
	 */
	public class PlayerAppearanceInfo extends Bean {
	
		//玩家性别
		private var _sex: int;
		
		//衣服模版ID
		private var _clothingmodid: int;
		
		//武器模版ID
		private var _weaponmodid: int;
		
		//武器强化等级
		private var _weaponStreng: int;
		
		//坐骑模版ID
		private var _horsemodid: int;
		
		//坐骑模版ID
		private var _horseweaponmodid: int;
		
		//头像模板ID
		private var _avatarid: int;
		
		//弓箭模板ID
		private var _arrowid: int;
		
		//暗器模版ID
		private var _hiddenweaponmodid: int;
		
		//坐骑锻骨草使用数量
		private var _horseduangu: int;
		
		//护身符模版ID
		private var _amuletModel: int;
		
		//披风模版ID
		private var _cloakModel: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家性别
			writeByte(_sex);
			//衣服模版ID
			writeInt(_clothingmodid);
			//武器模版ID
			writeInt(_weaponmodid);
			//武器强化等级
			writeByte(_weaponStreng);
			//坐骑模版ID
			writeInt(_horsemodid);
			//坐骑模版ID
			writeInt(_horseweaponmodid);
			//头像模板ID
			writeInt(_avatarid);
			//弓箭模板ID
			writeInt(_arrowid);
			//暗器模版ID
			writeInt(_hiddenweaponmodid);
			//坐骑锻骨草使用数量
			writeShort(_horseduangu);
			//护身符模版ID
			writeInt(_amuletModel);
			//披风模版ID
			writeInt(_cloakModel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家性别
			_sex = readByte();
			//衣服模版ID
			_clothingmodid = readInt();
			//武器模版ID
			_weaponmodid = readInt();
			//武器强化等级
			_weaponStreng = readByte();
			//坐骑模版ID
			_horsemodid = readInt();
			//坐骑模版ID
			_horseweaponmodid = readInt();
			//头像模板ID
			_avatarid = readInt();
			//弓箭模板ID
			_arrowid = readInt();
			//暗器模版ID
			_hiddenweaponmodid = readInt();
			//坐骑锻骨草使用数量
			_horseduangu = readShort();
			//护身符模版ID
			_amuletModel = readInt();
			//披风模版ID
			_cloakModel = readInt();
			return true;
		}
		
		/**
		 * get 玩家性别
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 玩家性别
		 */
		public function set sex(value: int): void{
			this._sex = value;
		}
		
		/**
		 * get 衣服模版ID
		 * @return 
		 */
		public function get clothingmodid(): int{
			return _clothingmodid;
		}
		
		/**
		 * set 衣服模版ID
		 */
		public function set clothingmodid(value: int): void{
			this._clothingmodid = value;
		}
		
		/**
		 * get 武器模版ID
		 * @return 
		 */
		public function get weaponmodid(): int{
			return _weaponmodid;
		}
		
		/**
		 * set 武器模版ID
		 */
		public function set weaponmodid(value: int): void{
			this._weaponmodid = value;
		}
		
		/**
		 * get 武器强化等级
		 * @return 
		 */
		public function get weaponStreng(): int{
			return _weaponStreng;
		}
		
		/**
		 * set 武器强化等级
		 */
		public function set weaponStreng(value: int): void{
			this._weaponStreng = value;
		}
		
		/**
		 * get 坐骑模版ID
		 * @return 
		 */
		public function get horsemodid(): int{
			return _horsemodid;
		}
		
		/**
		 * set 坐骑模版ID
		 */
		public function set horsemodid(value: int): void{
			this._horsemodid = value;
		}
		
		/**
		 * get 坐骑模版ID
		 * @return 
		 */
		public function get horseweaponmodid(): int{
			return _horseweaponmodid;
		}
		
		/**
		 * set 坐骑模版ID
		 */
		public function set horseweaponmodid(value: int): void{
			this._horseweaponmodid = value;
		}
		
		/**
		 * get 头像模板ID
		 * @return 
		 */
		public function get avatarid(): int{
			return _avatarid;
		}
		
		/**
		 * set 头像模板ID
		 */
		public function set avatarid(value: int): void{
			this._avatarid = value;
		}
		
		/**
		 * get 弓箭模板ID
		 * @return 
		 */
		public function get arrowid(): int{
			return _arrowid;
		}
		
		/**
		 * set 弓箭模板ID
		 */
		public function set arrowid(value: int): void{
			this._arrowid = value;
		}
		
		/**
		 * get 暗器模版ID
		 * @return 
		 */
		public function get hiddenweaponmodid(): int{
			return _hiddenweaponmodid;
		}
		
		/**
		 * set 暗器模版ID
		 */
		public function set hiddenweaponmodid(value: int): void{
			this._hiddenweaponmodid = value;
		}
		
		/**
		 * get 坐骑锻骨草使用数量
		 * @return 
		 */
		public function get horseduangu(): int{
			return _horseduangu;
		}
		
		/**
		 * set 坐骑锻骨草使用数量
		 */
		public function set horseduangu(value: int): void{
			this._horseduangu = value;
		}
		
		/**
		 * get 护身符模版ID
		 * @return 
		 */
		public function get amuletModel(): int{
			return _amuletModel;
		}
		
		/**
		 * set 护身符模版ID
		 */
		public function set amuletModel(value: int): void{
			this._amuletModel = value;
		}
		
		/**
		 * get 披风模版ID
		 * @return 
		 */
		public function get cloakModel(): int{
			return _cloakModel;
		}
		
		/**
		 * set 披风模版ID
		 */
		public function set cloakModel(value: int): void{
			this._cloakModel = value;
		}
		
	}
}