package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_spirittree_pack_con
	 */
	public class Q_spirittree_pack_con extends Bean{
	
		//ID编号(ID不能重编，只能添加)
		private var _q_id: int;
		
		//组包ID
		private var _q_packet_id: int;
		
		//物品ID
		private var _q_item_id: int;
		
		//物品件数
		private var _q_item_num: int;
		
		//可被偷取百分比（0不可偷取，>0表示可以被偷取，例如：30表示最多可以被偷走30%）
		private var _q_Theft_Percentage: int;
		
		//被偷后为主人补偿经验值
		private var _q_compensate_exp: int;
		
		//采摘后是否立即使用（0不立即使用，1立即使用）
		private var _q_is_use: int;
		
		//使用时在面板上播放光效资源（资源编号|拼接个数|持续秒数）
		private var _q_use_effect: String;
		
		//参数所需人物等级
		private var _q_need_level: int;
		
		//排除所需人物等级
		private var _q_exclude_level: int;
		
		//中选几率（互斥几率）
		private var _q_selected_rnd: int;
		
		//生成强化等级
		private var _q_streng_level: int;
		
		//生成附加属性条数
		private var _q_addProperty_num: int;
		
		//生成附加属性比例区间min（百分比分子）
		private var _q_addProperty_min: int;
		
		//生成附加属性比例区间max（百分比分子）
		private var _q_addProperty_max: int;
		
		//变更绑定类型为（0不绑定；1获得时绑定；2使用后绑定）
		private var _q_is_binding: int;
		
		//变更存在时间为（秒）
		private var _q_existtime: int;
		
		//采摘后发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
		private var _q_notice_type: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//ID编号(ID不能重编，只能添加)
			_q_id = readInt();
			//组包ID
			_q_packet_id = readInt();
			//物品ID
			_q_item_id = readInt();
			//物品件数
			_q_item_num = readInt();
			//可被偷取百分比（0不可偷取，>0表示可以被偷取，例如：30表示最多可以被偷走30%）
			_q_Theft_Percentage = readInt();
			//被偷后为主人补偿经验值
			_q_compensate_exp = readInt();
			//采摘后是否立即使用（0不立即使用，1立即使用）
			_q_is_use = readInt();
			//使用时在面板上播放光效资源（资源编号|拼接个数|持续秒数）
			_q_use_effect = readString();
			//参数所需人物等级
			_q_need_level = readInt();
			//排除所需人物等级
			_q_exclude_level = readInt();
			//中选几率（互斥几率）
			_q_selected_rnd = readInt();
			//生成强化等级
			_q_streng_level = readInt();
			//生成附加属性条数
			_q_addProperty_num = readInt();
			//生成附加属性比例区间min（百分比分子）
			_q_addProperty_min = readInt();
			//生成附加属性比例区间max（百分比分子）
			_q_addProperty_max = readInt();
			//变更绑定类型为（0不绑定；1获得时绑定；2使用后绑定）
			_q_is_binding = readInt();
			//变更存在时间为（秒）
			_q_existtime = readInt();
			//采摘后发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
			_q_notice_type = readInt();
			return true;
		}
		
		/**
		 * get ID编号(ID不能重编，只能添加)
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set ID编号(ID不能重编，只能添加)
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 组包ID
		 * @return 
		 */
		public function get q_packet_id(): int{
			return _q_packet_id;
		}
		
		/**
		 * set 组包ID
		 */
		public function set q_packet_id(value: int): void{
			this._q_packet_id = value;
		}
		
		/**
		 * get 物品ID
		 * @return 
		 */
		public function get q_item_id(): int{
			return _q_item_id;
		}
		
		/**
		 * set 物品ID
		 */
		public function set q_item_id(value: int): void{
			this._q_item_id = value;
		}
		
		/**
		 * get 物品件数
		 * @return 
		 */
		public function get q_item_num(): int{
			return _q_item_num;
		}
		
		/**
		 * set 物品件数
		 */
		public function set q_item_num(value: int): void{
			this._q_item_num = value;
		}
		
		/**
		 * get 可被偷取百分比（0不可偷取，>0表示可以被偷取，例如：30表示最多可以被偷走30%）
		 * @return 
		 */
		public function get q_Theft_Percentage(): int{
			return _q_Theft_Percentage;
		}
		
		/**
		 * set 可被偷取百分比（0不可偷取，>0表示可以被偷取，例如：30表示最多可以被偷走30%）
		 */
		public function set q_Theft_Percentage(value: int): void{
			this._q_Theft_Percentage = value;
		}
		
		/**
		 * get 被偷后为主人补偿经验值
		 * @return 
		 */
		public function get q_compensate_exp(): int{
			return _q_compensate_exp;
		}
		
		/**
		 * set 被偷后为主人补偿经验值
		 */
		public function set q_compensate_exp(value: int): void{
			this._q_compensate_exp = value;
		}
		
		/**
		 * get 采摘后是否立即使用（0不立即使用，1立即使用）
		 * @return 
		 */
		public function get q_is_use(): int{
			return _q_is_use;
		}
		
		/**
		 * set 采摘后是否立即使用（0不立即使用，1立即使用）
		 */
		public function set q_is_use(value: int): void{
			this._q_is_use = value;
		}
		
		/**
		 * get 使用时在面板上播放光效资源（资源编号|拼接个数|持续秒数）
		 * @return 
		 */
		public function get q_use_effect(): String{
			return _q_use_effect;
		}
		
		/**
		 * set 使用时在面板上播放光效资源（资源编号|拼接个数|持续秒数）
		 */
		public function set q_use_effect(value: String): void{
			this._q_use_effect = value;
		}
		
		/**
		 * get 参数所需人物等级
		 * @return 
		 */
		public function get q_need_level(): int{
			return _q_need_level;
		}
		
		/**
		 * set 参数所需人物等级
		 */
		public function set q_need_level(value: int): void{
			this._q_need_level = value;
		}
		
		/**
		 * get 排除所需人物等级
		 * @return 
		 */
		public function get q_exclude_level(): int{
			return _q_exclude_level;
		}
		
		/**
		 * set 排除所需人物等级
		 */
		public function set q_exclude_level(value: int): void{
			this._q_exclude_level = value;
		}
		
		/**
		 * get 中选几率（互斥几率）
		 * @return 
		 */
		public function get q_selected_rnd(): int{
			return _q_selected_rnd;
		}
		
		/**
		 * set 中选几率（互斥几率）
		 */
		public function set q_selected_rnd(value: int): void{
			this._q_selected_rnd = value;
		}
		
		/**
		 * get 生成强化等级
		 * @return 
		 */
		public function get q_streng_level(): int{
			return _q_streng_level;
		}
		
		/**
		 * set 生成强化等级
		 */
		public function set q_streng_level(value: int): void{
			this._q_streng_level = value;
		}
		
		/**
		 * get 生成附加属性条数
		 * @return 
		 */
		public function get q_addProperty_num(): int{
			return _q_addProperty_num;
		}
		
		/**
		 * set 生成附加属性条数
		 */
		public function set q_addProperty_num(value: int): void{
			this._q_addProperty_num = value;
		}
		
		/**
		 * get 生成附加属性比例区间min（百分比分子）
		 * @return 
		 */
		public function get q_addProperty_min(): int{
			return _q_addProperty_min;
		}
		
		/**
		 * set 生成附加属性比例区间min（百分比分子）
		 */
		public function set q_addProperty_min(value: int): void{
			this._q_addProperty_min = value;
		}
		
		/**
		 * get 生成附加属性比例区间max（百分比分子）
		 * @return 
		 */
		public function get q_addProperty_max(): int{
			return _q_addProperty_max;
		}
		
		/**
		 * set 生成附加属性比例区间max（百分比分子）
		 */
		public function set q_addProperty_max(value: int): void{
			this._q_addProperty_max = value;
		}
		
		/**
		 * get 变更绑定类型为（0不绑定；1获得时绑定；2使用后绑定）
		 * @return 
		 */
		public function get q_is_binding(): int{
			return _q_is_binding;
		}
		
		/**
		 * set 变更绑定类型为（0不绑定；1获得时绑定；2使用后绑定）
		 */
		public function set q_is_binding(value: int): void{
			this._q_is_binding = value;
		}
		
		/**
		 * get 变更存在时间为（秒）
		 * @return 
		 */
		public function get q_existtime(): int{
			return _q_existtime;
		}
		
		/**
		 * set 变更存在时间为（秒）
		 */
		public function set q_existtime(value: int): void{
			this._q_existtime = value;
		}
		
		/**
		 * get 采摘后发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
		 * @return 
		 */
		public function get q_notice_type(): int{
			return _q_notice_type;
		}
		
		/**
		 * set 采摘后发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
		 */
		public function set q_notice_type(value: int): void{
			this._q_notice_type = value;
		}
		
	}
}