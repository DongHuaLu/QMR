package datasets.bean{
	import com.game.message.Bean;
	
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_item
	 */
	public class Q_item extends Bean{
	
		//物品ID
		private var _q_id: int;
		
		//物品名称(最大九个汉字。支持_后缀，后缀部分在客户端不予显示)（物品名称需要唯一，以便在爆率表中使用汉字）
		private var _q_name: String;
		
		//物品类型
		private var _q_type: int;
		
		//佩戴性别需求（0全性别通用，1男，2女，）
		private var _q_sex: int;
		
		//绑定类型（0不绑定；1获得时绑定；2使用后绑定）
		private var _q_bind: int;
		
		//最大叠加数量（0或1均为不可叠加，最大可以填9999）
		private var _q_max: int;
		
		//商店中的购买价格
		private var _q_buy_price: int;
		
		//商店是否回收（0不回收，1回收）
		private var _q_sell: int;
		
		//商店回收价格
		private var _q_sell_price: int;
		
		//回收时是否弹出二次确认面板（0不弹出，1弹出）
		private var _q_sell_confirm: int;
		
		//是否可丢弃（0不可丢弃，1可丢弃）
		private var _q_drop: int;
		
		//丢弃时是否弹出二次确认面板（0不弹出，1弹出）
		private var _q_drop_confirm: int;
		
		//是否允许被拖放至物品快捷栏（0不允许，1允许）
		private var _q_shortcut: int;
		
		//佩戴部位
		private var _q_kind: int;
		
		//使用等级需求
		private var _q_level: int;
		
		//装备最大可强化等级
		private var _q_max_strengthen: int;
		
		//装备最大可镶嵌宝石数量
		private var _q_max_inlay: int;
		
		//基础攻击力
		private var _q_attack: int;
		
		//基础防御力
		private var _q_defence: int;
		
		//基础暴击值
		private var _q_crit: int;
		
		//基础闪避值
		private var _q_dodge: int;
		
		//基础生命上限
		private var _q_max_hp: int;
		
		//基础内力上限
		private var _q_max_mp: int;
		
		//基础体力上限
		private var _q_max_sp: int;
		
		//基础攻击速度
		private var _q_attackspeed: int;
		
		//基础移动速度
		private var _q_speed: int;
		
		//基础幸运值
		private var _q_luck: int;
		
		//使用后关联BUFF编号列表（格式：BUFF编号;BUFF编号;BUFF编号）
		private var _q_buff: String;
		
		//药品使用冷却时间（单位：毫秒）
		private var _q_cooldown: int;
		
		//药品公共冷却层级
		private var _q_cooldown_level: int;
		
		//公共冷却时间
		private var _q_cooldown_type: int;
		
		//传送卷轴类型（1为回城卷，2为随即传送卷，3为地图定点传送卷，4为行会回城卷）
		private var _q_transfer: int;
		
		//传送地图ID
		private var _q_transfer_map: int;
		
		//传送地图坐标X,Y
		private var _q_transfer_position: String;
		
		//使用后触发任务脚本编号
		private var _q_task: int;
		
		//使用后触发脚本编号
		private var _q_script: int;
		
		//使用后学会技能编号
		private var _q_skill: int;
		
		//使用后打开礼包表编号
		private var _q_gift: int;
		
		//使用后打开面板编号
		private var _q_ui: int;
		
		//怪物爆出件数上限阀值（填0为不限制）（格式：件数/天数）例如：2/3 是指：每3天最多爆出2件
		private var _q_max_create: String;
		
		//掉出时发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
		private var _q_notice: int;
		
		//是否记录产出与操作日志（0不记录；1记录）
		private var _q_log: int;
		
		//物品天生的名字颜色（0白色，1黄色，2绿色，3蓝色，4紫色，5橙色）(讨伐卷轴根据颜色编号关联任务)
		private var _q_default: int;
		
		//物品ICO上附加的光效资源编号
		private var _q_append: String;
		
		//物品描述信息（本处需要支持html标记）
		private var _q_describe: String;
		
		//物品微图标资源编号
		private var _q_tiny_icon: int;
		
		//物品换装资源编号（装备类物品使用）
		private var _q_equip_resource: String;
		
		//物品掉出时播放的音效资源编号
		private var _q_drop_music: String;
		
		//物品使用时播放的音效资源编号
		private var _q_use_music: String;
		
		//物品脱下时播放的音效资源编号
		private var _q_unuse_music: String;
		
		//药效编号(1恢复生命值,2恢复内力值,3恢复体力值,4增加经验,5增加真气储量,6增加等级,7增加攻击力,8增加防御力,9增加暴击,10增加闪避,11增加攻击速度,12增加移动速度,13增加生命上限值,14增加内力上限值,15增加体力上限值） 格式示例[1,2,4]
		private var _q_effict_type: String;
		
		//是否自动使用（1是，0否）
		private var _q_auto_use: int;
		
		//道具是否可批量使用(0否，1是)
		private var _q_whether_batch: int;
		
		//道具是否可进入仓库（1否，0是）
		private var _q_save_warehouse: int;
		
		//道具tips中的天生颜色
		private var _q_tips_effect: int;
		
		//物品使用上限
		private var _q_item_limit: int;
		
		//衰减循环数量
		private var _q_amount_attenuate: int;
		
		//最大衰减次数
		private var _q_time_attenuate: int;
		
		//衰减系数（万分比）
		private var _q_coefficient_attenuate: int;
		
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品ID
			_q_id = readInt();
			//物品名称(最大九个汉字。支持_后缀，后缀部分在客户端不予显示)（物品名称需要唯一，以便在爆率表中使用汉字）
			_q_name = readString();
			//物品类型
			_q_type = readInt();
			//佩戴性别需求（0全性别通用，1男，2女，）
			_q_sex = readInt();
			//绑定类型（0不绑定；1获得时绑定；2使用后绑定）
			_q_bind = readInt();
			//最大叠加数量（0或1均为不可叠加，最大可以填9999）
			_q_max = readInt();
			//商店中的购买价格
			_q_buy_price = readInt();
			//商店是否回收（0不回收，1回收）
			_q_sell = readInt();
			//商店回收价格
			_q_sell_price = readInt();
			//回收时是否弹出二次确认面板（0不弹出，1弹出）
			_q_sell_confirm = readInt();
			//是否可丢弃（0不可丢弃，1可丢弃）
			_q_drop = readInt();
			//丢弃时是否弹出二次确认面板（0不弹出，1弹出）
			_q_drop_confirm = readInt();
			//是否允许被拖放至物品快捷栏（0不允许，1允许）
			_q_shortcut = readInt();
			//佩戴部位
			_q_kind = readInt();
			//使用等级需求
			_q_level = readInt();
			//装备最大可强化等级
			_q_max_strengthen = readInt();
			//装备最大可镶嵌宝石数量
			_q_max_inlay = readInt();
			//基础攻击力
			_q_attack = readInt();
			//基础防御力
			_q_defence = readInt();
			//基础暴击值
			_q_crit = readInt();
			//基础闪避值
			_q_dodge = readInt();
			//基础生命上限
			_q_max_hp = readInt();
			//基础内力上限
			_q_max_mp = readInt();
			//基础体力上限
			_q_max_sp = readInt();
			//基础攻击速度
			_q_attackspeed = readInt();
			//基础移动速度
			_q_speed = readInt();
			//基础幸运值
			_q_luck = readInt();
			//使用后关联BUFF编号列表（格式：BUFF编号;BUFF编号;BUFF编号）
			_q_buff = readString();
			//药品使用冷却时间（单位：毫秒）
			_q_cooldown = readInt();
			//药品公共冷却层级
			_q_cooldown_level = readInt();
			//公共冷却时间
			_q_cooldown_type = readInt();
			//传送卷轴类型（1为回城卷，2为随即传送卷，3为地图定点传送卷，4为行会回城卷）
			_q_transfer = readInt();
			//传送地图ID
			_q_transfer_map = readInt();
			//传送地图坐标X,Y
			_q_transfer_position = readString();
			//使用后触发任务脚本编号
			_q_task = readInt();
			//使用后触发脚本编号
			_q_script = readInt();
			//使用后学会技能编号
			_q_skill = readInt();
			//使用后打开礼包表编号
			_q_gift = readInt();
			//使用后打开面板编号
			_q_ui = readInt();
			//怪物爆出件数上限阀值（填0为不限制）（格式：件数/天数）例如：2/3 是指：每3天最多爆出2件
			_q_max_create = readString();
			//掉出时发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
			_q_notice = readInt();
			//是否记录产出与操作日志（0不记录；1记录）
			_q_log = readInt();
			//物品天生的名字颜色（0白色，1黄色，2绿色，3蓝色，4紫色，5橙色）(讨伐卷轴根据颜色编号关联任务)
			_q_default = readInt();
			//物品ICO上附加的光效资源编号
			_q_append = readString();
			//物品描述信息（本处需要支持html标记）
			_q_describe = readString();
			//物品微图标资源编号
			_q_tiny_icon = readInt();
			//物品换装资源编号（装备类物品使用）
			_q_equip_resource = readString();
			//物品掉出时播放的音效资源编号
			_q_drop_music = readString();
			//物品使用时播放的音效资源编号
			_q_use_music = readString();
			//物品脱下时播放的音效资源编号
			_q_unuse_music = readString();
			//药效编号(1恢复生命值,2恢复内力值,3恢复体力值,4增加经验,5增加真气储量,6增加等级,7增加攻击力,8增加防御力,9增加暴击,10增加闪避,11增加攻击速度,12增加移动速度,13增加生命上限值,14增加内力上限值,15增加体力上限值） 格式示例[1,2,4]
			_q_effict_type = readString();
			//是否自动使用（1是，0否）
			_q_auto_use = readInt();
			//道具是否可批量使用(0否，1是)
			_q_whether_batch = readInt();
			//道具是否可进入仓库（1否，0是）
			_q_save_warehouse = readInt();
			//道具tips中的天生颜色
			_q_tips_effect = readInt();
			//物品使用上限
			_q_item_limit = readInt();
			//衰减循环数量
			_q_amount_attenuate = readInt();
			//最大衰减次数
			_q_time_attenuate = readInt();
			//衰减系数（万分比）
			_q_coefficient_attenuate = readInt();
			return true;
		}
		
		/**
		 * get 物品ID
		 * @return 
		 */
		public function get q_id(): int{
			return _q_id;
		}
		
		/**
		 * set 物品ID
		 */
		public function set q_id(value: int): void{
			this._q_id = value;
		}
		
		/**
		 * get 物品名称(最大九个汉字。支持_后缀，后缀部分在客户端不予显示)（物品名称需要唯一，以便在爆率表中使用汉字）
		 * @return 
		 */
		public function get q_name(): String{
			return _q_name;
		}
		
		/**
		 * set 物品名称(最大九个汉字。支持_后缀，后缀部分在客户端不予显示)（物品名称需要唯一，以便在爆率表中使用汉字）
		 */
		public function set q_name(value: String): void{
			this._q_name = value;
		}
		
		/**
		 * get 物品类型
		 * @return 
		 */
		public function get q_type(): int{
			return _q_type;
		}
		
		/**
		 * set 物品类型
		 */
		public function set q_type(value: int): void{
			this._q_type = value;
		}
		
		/**
		 * get 佩戴性别需求（0全性别通用，1男，2女，）
		 * @return 
		 */
		public function get q_sex(): int{
			return _q_sex;
		}
		
		/**
		 * set 佩戴性别需求（0全性别通用，1男，2女，）
		 */
		public function set q_sex(value: int): void{
			this._q_sex = value;
		}
		
		/**
		 * get 绑定类型（0不绑定；1获得时绑定；2使用后绑定）
		 * @return 
		 */
		public function get q_bind(): int{
			return _q_bind;
		}
		
		/**
		 * set 绑定类型（0不绑定；1获得时绑定；2使用后绑定）
		 */
		public function set q_bind(value: int): void{
			this._q_bind = value;
		}
		
		/**
		 * get 最大叠加数量（0或1均为不可叠加，最大可以填9999）
		 * @return 
		 */
		public function get q_max(): int{
			return _q_max;
		}
		
		/**
		 * set 最大叠加数量（0或1均为不可叠加，最大可以填9999）
		 */
		public function set q_max(value: int): void{
			this._q_max = value;
		}
		
		/**
		 * get 商店中的购买价格
		 * @return 
		 */
		public function get q_buy_price(): int{
			return _q_buy_price;
		}
		
		/**
		 * set 商店中的购买价格
		 */
		public function set q_buy_price(value: int): void{
			this._q_buy_price = value;
		}
		
		/**
		 * get 商店是否回收（0不回收，1回收）
		 * @return 
		 */
		public function get q_sell(): int{
			return _q_sell;
		}
		
		/**
		 * set 商店是否回收（0不回收，1回收）
		 */
		public function set q_sell(value: int): void{
			this._q_sell = value;
		}
		
		/**
		 * get 商店回收价格
		 * @return 
		 */
		public function get q_sell_price(): int{
			return _q_sell_price;
		}
		
		/**
		 * set 商店回收价格
		 */
		public function set q_sell_price(value: int): void{
			this._q_sell_price = value;
		}
		
		/**
		 * get 回收时是否弹出二次确认面板（0不弹出，1弹出）
		 * @return 
		 */
		public function get q_sell_confirm(): int{
			return _q_sell_confirm;
		}
		
		/**
		 * set 回收时是否弹出二次确认面板（0不弹出，1弹出）
		 */
		public function set q_sell_confirm(value: int): void{
			this._q_sell_confirm = value;
		}
		
		/**
		 * get 是否可丢弃（0不可丢弃，1可丢弃）
		 * @return 
		 */
		public function get q_drop(): int{
			return _q_drop;
		}
		
		/**
		 * set 是否可丢弃（0不可丢弃，1可丢弃）
		 */
		public function set q_drop(value: int): void{
			this._q_drop = value;
		}
		
		/**
		 * get 丢弃时是否弹出二次确认面板（0不弹出，1弹出）
		 * @return 
		 */
		public function get q_drop_confirm(): int{
			return _q_drop_confirm;
		}
		
		/**
		 * set 丢弃时是否弹出二次确认面板（0不弹出，1弹出）
		 */
		public function set q_drop_confirm(value: int): void{
			this._q_drop_confirm = value;
		}
		
		/**
		 * get 是否允许被拖放至物品快捷栏（0不允许，1允许）
		 * @return 
		 */
		public function get q_shortcut(): int{
			return _q_shortcut;
		}
		
		/**
		 * set 是否允许被拖放至物品快捷栏（0不允许，1允许）
		 */
		public function set q_shortcut(value: int): void{
			this._q_shortcut = value;
		}
		
		/**
		 * get 佩戴部位
		 * @return 
		 */
		public function get q_kind(): int{
			return _q_kind;
		}
		
		/**
		 * set 佩戴部位
		 */
		public function set q_kind(value: int): void{
			this._q_kind = value;
		}
		
		/**
		 * get 使用等级需求
		 * @return 
		 */
		public function get q_level(): int{
			return _q_level;
		}
		
		/**
		 * set 使用等级需求
		 */
		public function set q_level(value: int): void{
			this._q_level = value;
		}
		
		/**
		 * get 装备最大可强化等级
		 * @return 
		 */
		public function get q_max_strengthen(): int{
			return _q_max_strengthen;
		}
		
		/**
		 * set 装备最大可强化等级
		 */
		public function set q_max_strengthen(value: int): void{
			this._q_max_strengthen = value;
		}
		
		/**
		 * get 装备最大可镶嵌宝石数量
		 * @return 
		 */
		public function get q_max_inlay(): int{
			return _q_max_inlay;
		}
		
		/**
		 * set 装备最大可镶嵌宝石数量
		 */
		public function set q_max_inlay(value: int): void{
			this._q_max_inlay = value;
		}
		
		/**
		 * get 基础攻击力
		 * @return 
		 */
		public function get q_attack(): int{
			return _q_attack;
		}
		
		/**
		 * set 基础攻击力
		 */
		public function set q_attack(value: int): void{
			this._q_attack = value;
		}
		
		/**
		 * get 基础防御力
		 * @return 
		 */
		public function get q_defence(): int{
			return _q_defence;
		}
		
		/**
		 * set 基础防御力
		 */
		public function set q_defence(value: int): void{
			this._q_defence = value;
		}
		
		/**
		 * get 基础暴击值
		 * @return 
		 */
		public function get q_crit(): int{
			return _q_crit;
		}
		
		/**
		 * set 基础暴击值
		 */
		public function set q_crit(value: int): void{
			this._q_crit = value;
		}
		
		/**
		 * get 基础闪避值
		 * @return 
		 */
		public function get q_dodge(): int{
			return _q_dodge;
		}
		
		/**
		 * set 基础闪避值
		 */
		public function set q_dodge(value: int): void{
			this._q_dodge = value;
		}
		
		/**
		 * get 基础生命上限
		 * @return 
		 */
		public function get q_max_hp(): int{
			return _q_max_hp;
		}
		
		/**
		 * set 基础生命上限
		 */
		public function set q_max_hp(value: int): void{
			this._q_max_hp = value;
		}
		
		/**
		 * get 基础内力上限
		 * @return 
		 */
		public function get q_max_mp(): int{
			return _q_max_mp;
		}
		
		/**
		 * set 基础内力上限
		 */
		public function set q_max_mp(value: int): void{
			this._q_max_mp = value;
		}
		
		/**
		 * get 基础体力上限
		 * @return 
		 */
		public function get q_max_sp(): int{
			return _q_max_sp;
		}
		
		/**
		 * set 基础体力上限
		 */
		public function set q_max_sp(value: int): void{
			this._q_max_sp = value;
		}
		
		/**
		 * get 基础攻击速度
		 * @return 
		 */
		public function get q_attackspeed(): int{
			return _q_attackspeed;
		}
		
		/**
		 * set 基础攻击速度
		 */
		public function set q_attackspeed(value: int): void{
			this._q_attackspeed = value;
		}
		
		/**
		 * get 基础移动速度
		 * @return 
		 */
		public function get q_speed(): int{
			return _q_speed;
		}
		
		/**
		 * set 基础移动速度
		 */
		public function set q_speed(value: int): void{
			this._q_speed = value;
		}
		
		/**
		 * get 基础幸运值
		 * @return 
		 */
		public function get q_luck(): int{
			return _q_luck;
		}
		
		/**
		 * set 基础幸运值
		 */
		public function set q_luck(value: int): void{
			this._q_luck = value;
		}
		
		/**
		 * get 使用后关联BUFF编号列表（格式：BUFF编号;BUFF编号;BUFF编号）
		 * @return 
		 */
		public function get q_buff(): String{
			return _q_buff;
		}
		
		/**
		 * set 使用后关联BUFF编号列表（格式：BUFF编号;BUFF编号;BUFF编号）
		 */
		public function set q_buff(value: String): void{
			this._q_buff = value;
		}
		
		/**
		 * get 药品使用冷却时间（单位：毫秒）
		 * @return 
		 */
		public function get q_cooldown(): int{
			return _q_cooldown;
		}
		
		/**
		 * set 药品使用冷却时间（单位：毫秒）
		 */
		public function set q_cooldown(value: int): void{
			this._q_cooldown = value;
		}
		
		/**
		 * get 药品公共冷却层级
		 * @return 
		 */
		public function get q_cooldown_level(): int{
			return _q_cooldown_level;
		}
		
		/**
		 * set 药品公共冷却层级
		 */
		public function set q_cooldown_level(value: int): void{
			this._q_cooldown_level = value;
		}
		
		/**
		 * get 公共冷却时间
		 * @return 
		 */
		public function get q_cooldown_type(): int{
			return _q_cooldown_type;
		}
		
		/**
		 * set 公共冷却时间
		 */
		public function set q_cooldown_type(value: int): void{
			this._q_cooldown_type = value;
		}
		
		/**
		 * get 传送卷轴类型（1为回城卷，2为随即传送卷，3为地图定点传送卷，4为行会回城卷）
		 * @return 
		 */
		public function get q_transfer(): int{
			return _q_transfer;
		}
		
		/**
		 * set 传送卷轴类型（1为回城卷，2为随即传送卷，3为地图定点传送卷，4为行会回城卷）
		 */
		public function set q_transfer(value: int): void{
			this._q_transfer = value;
		}
		
		/**
		 * get 传送地图ID
		 * @return 
		 */
		public function get q_transfer_map(): int{
			return _q_transfer_map;
		}
		
		/**
		 * set 传送地图ID
		 */
		public function set q_transfer_map(value: int): void{
			this._q_transfer_map = value;
		}
		
		/**
		 * get 传送地图坐标X,Y
		 * @return 
		 */
		public function get q_transfer_position(): String{
			return _q_transfer_position;
		}
		
		/**
		 * set 传送地图坐标X,Y
		 */
		public function set q_transfer_position(value: String): void{
			this._q_transfer_position = value;
		}
		
		/**
		 * get 使用后触发任务脚本编号
		 * @return 
		 */
		public function get q_task(): int{
			return _q_task;
		}
		
		/**
		 * set 使用后触发任务脚本编号
		 */
		public function set q_task(value: int): void{
			this._q_task = value;
		}
		
		/**
		 * get 使用后触发脚本编号
		 * @return 
		 */
		public function get q_script(): int{
			return _q_script;
		}
		
		/**
		 * set 使用后触发脚本编号
		 */
		public function set q_script(value: int): void{
			this._q_script = value;
		}
		
		/**
		 * get 使用后学会技能编号
		 * @return 
		 */
		public function get q_skill(): int{
			return _q_skill;
		}
		
		/**
		 * set 使用后学会技能编号
		 */
		public function set q_skill(value: int): void{
			this._q_skill = value;
		}
		
		/**
		 * get 使用后打开礼包表编号
		 * @return 
		 */
		public function get q_gift(): int{
			return _q_gift;
		}
		
		/**
		 * set 使用后打开礼包表编号
		 */
		public function set q_gift(value: int): void{
			this._q_gift = value;
		}
		
		/**
		 * get 使用后打开面板编号
		 * @return 
		 */
		public function get q_ui(): int{
			return _q_ui;
		}
		
		/**
		 * set 使用后打开面板编号
		 */
		public function set q_ui(value: int): void{
			this._q_ui = value;
		}
		
		/**
		 * get 怪物爆出件数上限阀值（填0为不限制）（格式：件数/天数）例如：2/3 是指：每3天最多爆出2件
		 * @return 
		 */
		public function get q_max_create(): String{
			return _q_max_create;
		}
		
		/**
		 * set 怪物爆出件数上限阀值（填0为不限制）（格式：件数/天数）例如：2/3 是指：每3天最多爆出2件
		 */
		public function set q_max_create(value: String): void{
			this._q_max_create = value;
		}
		
		/**
		 * get 掉出时发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
		 * @return 
		 */
		public function get q_notice(): int{
			return _q_notice;
		}
		
		/**
		 * set 掉出时发送的公告类型（0不发送公告；1面向个人发送聊天框内公告；2面向个人发送聊天框内与屏幕上方双重公告；3面向全服发送聊天框内公告；4面向全服发送聊天框内与屏幕上方双重公告）
		 */
		public function set q_notice(value: int): void{
			this._q_notice = value;
		}
		
		/**
		 * get 是否记录产出与操作日志（0不记录；1记录）
		 * @return 
		 */
		public function get q_log(): int{
			return _q_log;
		}
		
		/**
		 * set 是否记录产出与操作日志（0不记录；1记录）
		 */
		public function set q_log(value: int): void{
			this._q_log = value;
		}
		
		/**
		 * get 物品天生的名字颜色（0白色，1黄色，2绿色，3蓝色，4紫色，5橙色）(讨伐卷轴根据颜色编号关联任务)
		 * @return 
		 */
		public function get q_default(): int{
			return _q_default;
		}
		
		/**
		 * set 物品天生的名字颜色（0白色，1黄色，2绿色，3蓝色，4紫色，5橙色）(讨伐卷轴根据颜色编号关联任务)
		 */
		public function set q_default(value: int): void{
			this._q_default = value;
		}
		
		/**
		 * get 物品ICO上附加的光效资源编号
		 * @return 
		 */
		public function get q_append(): String{
			return _q_append;
		}
		
		/**
		 * set 物品ICO上附加的光效资源编号
		 */
		public function set q_append(value: String): void{
			this._q_append = value;
		}
		
		/**
		 * get 物品描述信息（本处需要支持html标记）
		 * @return 
		 */
		public function get q_describe(): String{
			return _q_describe;
		}
		
		/**
		 * set 物品描述信息（本处需要支持html标记）
		 */
		public function set q_describe(value: String): void{
			this._q_describe = value;
		}
		
		/**
		 * get 物品微图标资源编号
		 * @return 
		 */
		public function get q_tiny_icon(): int{
			return _q_tiny_icon;
		}
		
		/**
		 * set 物品微图标资源编号
		 */
		public function set q_tiny_icon(value: int): void{
			this._q_tiny_icon = value;
		}
		
		/**
		 * get 物品换装资源编号（装备类物品使用）
		 * @return 
		 */
		public function get q_equip_resource(): String{
			return _q_equip_resource;
		}
		
		/**
		 * set 物品换装资源编号（装备类物品使用）
		 */
		public function set q_equip_resource(value: String): void{
			this._q_equip_resource = value;
		}
		
		/**
		 * get 物品掉出时播放的音效资源编号
		 * @return 
		 */
		public function get q_drop_music(): String{
			return _q_drop_music;
		}
		
		/**
		 * set 物品掉出时播放的音效资源编号
		 */
		public function set q_drop_music(value: String): void{
			this._q_drop_music = value;
		}
		
		/**
		 * get 物品使用时播放的音效资源编号
		 * @return 
		 */
		public function get q_use_music(): String{
			return _q_use_music;
		}
		
		/**
		 * set 物品使用时播放的音效资源编号
		 */
		public function set q_use_music(value: String): void{
			this._q_use_music = value;
		}
		
		/**
		 * get 物品脱下时播放的音效资源编号
		 * @return 
		 */
		public function get q_unuse_music(): String{
			return _q_unuse_music;
		}
		
		/**
		 * set 物品脱下时播放的音效资源编号
		 */
		public function set q_unuse_music(value: String): void{
			this._q_unuse_music = value;
		}
		
		/**
		 * get 药效编号(1恢复生命值,2恢复内力值,3恢复体力值,4增加经验,5增加真气储量,6增加等级,7增加攻击力,8增加防御力,9增加暴击,10增加闪避,11增加攻击速度,12增加移动速度,13增加生命上限值,14增加内力上限值,15增加体力上限值） 格式示例[1,2,4]
		 * @return 
		 */
		public function get q_effict_type(): String{
			return _q_effict_type;
		}
		
		/**
		 * set 药效编号(1恢复生命值,2恢复内力值,3恢复体力值,4增加经验,5增加真气储量,6增加等级,7增加攻击力,8增加防御力,9增加暴击,10增加闪避,11增加攻击速度,12增加移动速度,13增加生命上限值,14增加内力上限值,15增加体力上限值） 格式示例[1,2,4]
		 */
		public function set q_effict_type(value: String): void{
			this._q_effict_type = value;
		}
		
		/**
		 * get 是否自动使用（1是，0否）
		 * @return 
		 */
		public function get q_auto_use(): int{
			return _q_auto_use;
		}
		
		/**
		 * set 是否自动使用（1是，0否）
		 */
		public function set q_auto_use(value: int): void{
			this._q_auto_use = value;
		}
		
		/**
		 * get 道具是否可批量使用(0否，1是)
		 * @return 
		 */
		public function get q_whether_batch(): int{
			return _q_whether_batch;
		}
		
		/**
		 * set 道具是否可批量使用(0否，1是)
		 */
		public function set q_whether_batch(value: int): void{
			this._q_whether_batch = value;
		}
		
		/**
		 * get 道具是否可进入仓库（1否，0是）
		 * @return 
		 */
		public function get q_save_warehouse(): int{
			return _q_save_warehouse;
		}
		
		/**
		 * set 道具是否可进入仓库（1否，0是）
		 */
		public function set q_save_warehouse(value: int): void{
			this._q_save_warehouse = value;
		}
		
		/**
		 * get 道具tips中的天生颜色
		 * @return 
		 */
		public function get q_tips_effect(): int{
			return _q_tips_effect;
		}
		
		/**
		 * set 道具tips中的天生颜色
		 */
		public function set q_tips_effect(value: int): void{
			this._q_tips_effect = value;
		}
		
		/**
		 * get 物品使用上限
		 * @return 
		 */
		public function get q_item_limit(): int{
			return _q_item_limit;
		}
		
		/**
		 * set 物品使用上限
		 */
		public function set q_item_limit(value: int): void{
			this._q_item_limit = value;
		}
		
		/**
		 * get 衰减循环数量
		 * @return 
		 */
		public function get q_amount_attenuate(): int{
			return _q_amount_attenuate;
		}
		
		/**
		 * set 衰减循环数量
		 */
		public function set q_amount_attenuate(value: int): void{
			this._q_amount_attenuate = value;
		}
		
		/**
		 * get 最大衰减次数
		 * @return 
		 */
		public function get q_time_attenuate(): int{
			return _q_time_attenuate;
		}
		
		/**
		 * set 最大衰减次数
		 */
		public function set q_time_attenuate(value: int): void{
			this._q_time_attenuate = value;
		}
		
		/**
		 * get 衰减系数（万分比）
		 * @return 
		 */
		public function get q_coefficient_attenuate(): int{
			return _q_coefficient_attenuate;
		}
		
		/**
		 * set 衰减系数（万分比）
		 */
		public function set q_coefficient_attenuate(value: int): void{
			this._q_coefficient_attenuate = value;
		}
		
	}
}