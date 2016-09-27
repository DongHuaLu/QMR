package com.game.chestbox.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宝箱盒子选择信息发送到客户端
	 */
	public class ResChestBoxChooseInfoToClientMessage extends Message {
	
		//开启次数
		private var _opennum: int;
		
		//内圈选中编号
		private var _inchooseidx: int;
		
		//外圈选中编号
		private var _outchooseidx: int;
		
		//外圈飞入内圈编号
		private var _chooseOutSideToInSide: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//开启次数
			writeInt(_opennum);
			//内圈选中编号
			writeInt(_inchooseidx);
			//外圈选中编号
			writeInt(_outchooseidx);
			//外圈飞入内圈编号
			writeInt(_chooseOutSideToInSide);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//开启次数
			_opennum = readInt();
			//内圈选中编号
			_inchooseidx = readInt();
			//外圈选中编号
			_outchooseidx = readInt();
			//外圈飞入内圈编号
			_chooseOutSideToInSide = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 156102;
		}
		
		/**
		 * get 开启次数
		 * @return 
		 */
		public function get opennum(): int{
			return _opennum;
		}
		
		/**
		 * set 开启次数
		 */
		public function set opennum(value: int): void{
			this._opennum = value;
		}
		
		/**
		 * get 内圈选中编号
		 * @return 
		 */
		public function get inchooseidx(): int{
			return _inchooseidx;
		}
		
		/**
		 * set 内圈选中编号
		 */
		public function set inchooseidx(value: int): void{
			this._inchooseidx = value;
		}
		
		/**
		 * get 外圈选中编号
		 * @return 
		 */
		public function get outchooseidx(): int{
			return _outchooseidx;
		}
		
		/**
		 * set 外圈选中编号
		 */
		public function set outchooseidx(value: int): void{
			this._outchooseidx = value;
		}
		
		/**
		 * get 外圈飞入内圈编号
		 * @return 
		 */
		public function get chooseOutSideToInSide(): int{
			return _chooseOutSideToInSide;
		}
		
		/**
		 * set 外圈飞入内圈编号
		 */
		public function set chooseOutSideToInSide(value: int): void{
			this._chooseOutSideToInSide = value;
		}
		
	}
}