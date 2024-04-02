import instance from './axios';

export async function getGroupInfo() {
  try {
    const res = await instance.get('/groups', {});
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function postGroupInfo(name: string) {
  const data = { name };
  console.log('data:', data);
  const blob = new Blob([JSON.stringify(data)], {
    type: 'application/json',
  });
  const formData = new FormData();
  formData.append('data', blob);

  try {
    console.log('formData:', formData);
    const res = await instance.post('/groups', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    console.log('결과는!', res);
    return res;
  } catch (e) {
    console.log(e);
  }
}

export async function getLog(groupId: number) {
  try {
    console.log('그룹아이디:', groupId);
    const res = await instance.get(`/groups/${groupId}/log`, {
      params: {
        page: 0,
        size: 15,
      },
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

interface groupInfoType {
  groupId: number;
  groupName: string;
}
export async function putGroupName({
  groupId,
  groupName,
}: groupInfoType) {
  const data = { name: groupName };
  try {
    console.log('그룹아이디:', groupId);
    const res = await instance.put(`/groups/${groupId}/name`, data);
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

interface groupImgType {
  groupId: number;
  groupImg: File;
}
export async function modifyGroupImage({
  groupId,
  groupImg,
}: groupImgType) {
  const formData = new FormData();
  formData.append('image', groupImg);
  try {
    const res = await instance.post(`/groups/${groupId}/image`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}
