// pages/component/page/feedback/feedback.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },
  /**
   * 表單提交事件
   */
  formSubmit: function (e) {
    var that = this;
    that.setData({
      info: e.detail.value.textarea
    })
    wx.request({
      url: that.data.url + '/wechat-nowait/feedBack/saveFeedBackInfo',
      data: {
        info: that.data.info,
        wxuserId: that.data.wxuserId
      },
      success: function (res) {
        console.log('反馈成功');
        console.log(res.errMsg);
        wx.switchTab({
          url: '/pages/user/user',
          success: function () {
            var page = getCurrentPages().pop();
            page.onLoad();
          }
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getStorage({
      key: 'url',
      success: function(res) {
        that.setData({
          url: res.data
        })
      },
    })
    wx.getStorage({
      key: 'wxUserId',
      success: function (res) {
        that.setData({
          wxuserId: res.data
        })
      },
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})