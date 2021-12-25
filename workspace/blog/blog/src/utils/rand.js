/**
 * 随机数获取
 * @param lower 最低值 包括
 * @param upper 最高值 不包括
 * */
export const random = function(lower, upper) {
  return Math.floor(Math.random() * (upper - lower)) + lower
}
