package com.example.likelion_pbl_spring.repository;

import com.example.likelion_pbl_spring.role.Member;
import org.springframework.stereotype.Repository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private final List<Member> store = new ArrayList<>();

    @Override
    public void save(String name, Member member) {
        store.add(member);
    }

    @Override
    public Member findByName(String name) {
        for (Member member : store) {
            String memberName = getMemberNameViaReflection(member);
            if (memberName != null && memberName.equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store);
    }

    @Override
    public void updateByName(String name, Member updatedMember) {
        for (int i = 0; i < store.size(); i++) {
            String memberName = getMemberNameViaReflection(store.get(i));
            if (memberName != null && memberName.equals(name)) {
                store.set(i, updatedMember);
                return;
            }
        }
    }

    @Override
    public boolean deleteByName(String name) {
        for (int i = 0; i < store.size(); i++) {
            String memberName = getMemberNameViaReflection(store.get(i));
            if (memberName != null && memberName.equals(name)) {
                store.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByName(String name) {
        for (Member member : store) {
            String memberName = getMemberNameViaReflection(member);
            if (memberName != null && memberName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 🔥 도메인 클래스를 단 한 줄도 수정하지 않기 위한 필살기 메서드
     * 자바 리플렉션 기술을 이용해 객체 내부의 'name' 필드 값을 직접 추출합니다.
     */
    private String getMemberNameViaReflection(Object object) {
        if (object == null) return null;

        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                // 객체 내부에 선언된 'name'이라는 필드를 강제로 찾아옵니다.
                Field field = clazz.getDeclaredField("name");
                field.setAccessible(true); // private 필드여도 접근할 수 있도록 권한을 엽니다.
                return (String) field.get(object);
            } catch (NoSuchFieldException e) {
                // 현재 클래스에 없으면 부모 클래스(Member) 방향으로 올라가며 다시 찾습니다.
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }
}