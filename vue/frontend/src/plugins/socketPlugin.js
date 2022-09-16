import Vue from "vue";
import io from "socket.io-client";

const socket = io("http://localhost:3000");

const SocketPlugin = {
  install(vue) {
    vue.mixin({});

    vue.prototype.$join = ($payload) => {
      socket.emit("join", $payload);
      console.log("님이 입장하셨습니다.");
    };

    vue.prototype.$leave = ($payload) => {
      socket.emit("leave", $payload);
      console.log("님이 퇴장하셨습니다.");
    };

    vue.prototype.$sendMessage = ($payload) => {
      socket.emit("chat", {
        msg: $payload.msg,
        name: $payload.name,
        avatar: $payload.avatar,
      });
    };

    vue.prototype.$socket = socket;
  },
};

Vue.use(SocketPlugin);
