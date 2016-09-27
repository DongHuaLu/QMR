package com.game.emperorcity.message{
	import com.game.emperorcity.bean.EmperorStructureInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回皇城结构信息
	 */
	public class ResEmperorStructureInfoToClientMessage extends Message {
	
		//皇城结构信息
		private var _emperorStructureInfo: EmperorStructureInfo;
		
		//奖励领取状态1表示已经领取，排列顺序 皇帝，皇后
		private var _stateawards: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//皇城结构信息
			writeBean(_emperorStructureInfo);
			//奖励领取状态1表示已经领取，排列顺序 皇帝，皇后
			writeShort(_stateawards.length);
			for (i = 0; i < _stateawards.length; i++) {
				writeByte(_stateawards[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//皇城结构信息
			_emperorStructureInfo = readBean(EmperorStructureInfo) as EmperorStructureInfo;
			//奖励领取状态1表示已经领取，排列顺序 皇帝，皇后
			var stateawards_length : int = readShort();
			for (i = 0; i < stateawards_length; i++) {
				_stateawards[i] = readByte();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169105;
		}
		
		/**
		 * get 皇城结构信息
		 * @return 
		 */
		public function get emperorStructureInfo(): EmperorStructureInfo{
			return _emperorStructureInfo;
		}
		
		/**
		 * set 皇城结构信息
		 */
		public function set emperorStructureInfo(value: EmperorStructureInfo): void{
			this._emperorStructureInfo = value;
		}
		
		/**
		 * get 奖励领取状态1表示已经领取，排列顺序 皇帝，皇后
		 * @return 
		 */
		public function get stateawards(): Vector.<int>{
			return _stateawards;
		}
		
		/**
		 * set 奖励领取状态1表示已经领取，排列顺序 皇帝，皇后
		 */
		public function set stateawards(value: Vector.<int>): void{
			this._stateawards = value;
		}
		
	}
}